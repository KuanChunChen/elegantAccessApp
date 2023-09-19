package com.elegant.access.service

/**
 * This file is part of an Android project developed by elegant.access.
 *
 * For more information about this project, you can visit our website:
 * {@link https://elegantaccess.org/2021/11/12/android-kotlin-bluetooth-gatt-client}
 *
 * This project demonstrates how to use Bluetooth GATT in Android with Kotlin.
 * It includes examples of connecting to a Bluetooth device, discovering services,
 * reading characteristics, and receiving notifications.
 *
 * Please note that this project is for educational purposes only and is not intended
 * for use in production environments.
 *
 * @author Willy.Chen
 * @version 1.0
 * @since 2020~2023
 */

import android.app.Service
import android.bluetooth.*
import android.bluetooth.BluetoothAdapter.STATE_CONNECTED
import android.bluetooth.BluetoothAdapter.STATE_DISCONNECTED
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.elegant.access.base.constant.BluetoothConstant.UUID_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR
import timber.log.Timber
import java.util.*

private val TAG = BluetoothLeService::class.java.simpleName
private const val STATE_DISCONNECTED = 0
private const val STATE_CONNECTING = 1
private const val STATE_CONNECTED = 2
const val ACTION_GATT_CONNECTED = "com.example.bluetooth.le.ACTION_GATT_CONNECTED"
const val ACTION_GATT_DISCONNECTED = "com.example.bluetooth.le.ACTION_GATT_DISCONNECTED"
const val ACTION_GATT_SERVICES_DISCOVERED =
    "com.example.bluetooth.le.ACTION_GATT_SERVICES_DISCOVERED"
const val ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE"
const val EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA"
val UUID_DEMO_DEVICE = UUID.fromString(UUID_CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR)!!

class BluetoothLeService() : Service() {



    private var connectionState = STATE_DISCONNECTED
    private val localBinder: IBinder = LocalBinder()
    private var bluetoothManager: BluetoothManager? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothGatt: BluetoothGatt? = null

    private var bluetoothDeviceAddress: String? = null

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(
            gatt: BluetoothGatt,
            status: Int,
            newState: Int
        ) {
            val intentAction: String
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    intentAction = ACTION_GATT_CONNECTED
                    connectionState = STATE_CONNECTED
                    broadcastUpdate(intentAction)
                    Timber.i("Connected to GATT server.")
                    Timber.i("Attempting to start service discovery: %s", bluetoothGatt?.discoverServices())
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    intentAction = ACTION_GATT_DISCONNECTED
                    connectionState = STATE_DISCONNECTED
                    Timber.i("Disconnected from GATT server.")
                    broadcastUpdate(intentAction)
                }
            }
        }

        // New services discovered
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            when (status) {
                BluetoothGatt.GATT_SUCCESS -> broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED)
                else -> Log.w(TAG, "onServicesDiscovered received: $status")
            }
        }

        // Result of a characteristic read operation
        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            status: Int
        ) {
            when (status) {
                BluetoothGatt.GATT_SUCCESS -> {
                    broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic)
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return localBinder
    }
    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }


    fun initialize(): Boolean {
        Log.w(TAG, "initialize")
        if (bluetoothManager == null) {
            bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            if (bluetoothManager == null) {
                Timber.e("Unable to initialize BluetoothManager.")
                return false
            }
        }

        bluetoothAdapter = bluetoothManager!!.adapter
        if (bluetoothAdapter == null) {
            Timber.e("Unable to obtain a BluetoothAdapter.")
            return false
        }

        return true
    }


    fun connect(address: String?): Boolean {
            Timber.d(address)
        if (bluetoothAdapter == null || address == null) {
            Timber.d("BluetoothAdapter not initialized or unspecified address.")
            return false
        }


        if (bluetoothDeviceAddress != null && address == bluetoothDeviceAddress
            && bluetoothGatt != null
        ) {
            Timber.d( "Trying to use an existing mBluetoothGatt for connection.")
            return if (bluetoothGatt!!.connect()) {
                connectionState = STATE_CONNECTING
                true
            } else {
                false
            }
        }

        val device = bluetoothAdapter!!.getRemoteDevice(address)
        if (device == null) {
            Timber.d( "Device not found.  Unable to connect.")
            return false
        }

        bluetoothGatt = device.connectGatt(this, false, gattCallback)
        Timber.d("Trying to create a new connection.")
        bluetoothDeviceAddress = address
        connectionState = STATE_CONNECTING
        return true
    }


    inner class LocalBinder : Binder() {
        internal val service: BluetoothLeService get() = this@BluetoothLeService
    }

    private fun broadcastUpdate(action: String) {
        val intent = Intent(action)
        sendBroadcast(intent)
    }

    private fun broadcastUpdate(action: String, characteristic: BluetoothGattCharacteristic) {
        val intent = Intent(action)


        when (characteristic.uuid) {
            UUID_DEMO_DEVICE -> {
                val flag = characteristic.properties
                val format = when (flag and 0x01) {
                    0x01 -> {
                        Log.d(TAG, "Heart rate format UINT16.")
                        BluetoothGattCharacteristic.FORMAT_UINT16
                    }
                    else -> {
                        Log.d(TAG, "Heart rate format UINT8.")
                        BluetoothGattCharacteristic.FORMAT_UINT8
                    }
                }
                val heartRate = characteristic.getIntValue(format, 1)
                Log.d(TAG, String.format("Received heart rate: %d", heartRate))
                intent.putExtra(EXTRA_DATA, (heartRate).toString())
            }
            else -> {
                val data: ByteArray? = characteristic.value
                if (data?.isNotEmpty() == true) {
                    val hexString: String = data.joinToString(separator = " ") {
                        String.format("%02X", it)
                    }
                    intent.putExtra(EXTRA_DATA, "$data\n$hexString")
                }
            }

        }
        sendBroadcast(intent)
    }
}