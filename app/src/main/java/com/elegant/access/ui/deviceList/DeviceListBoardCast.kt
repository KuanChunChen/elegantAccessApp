package com.elegant.access.ui.deviceList

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

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class DeviceListBoardCast(private val onBleDeviceFound: (bluetoothDevice: BleDevice) -> Unit = {}) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action) {
            BluetoothDevice.ACTION_FOUND -> {
                Timber.d("--Action found!--")
                val device: BluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)!!
                val rssi = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI, Short.MIN_VALUE).toInt()
//                val bleDevice = if (null == device.name) {
//                    BleDevice("Unknown Device", device.address, rssi)
//                } else {
//                    BleDevice(device.name, device.address, rssi)
//                }

                val uuidExtra = intent.getParcelableArrayExtra(BluetoothDevice.EXTRA_UUID)
                println("DeviceExtra address - " + device.address)
                if (uuidExtra != null) {
                    for (p in uuidExtra) {
                        println("uuidExtra - $p")
                    }
                } else {
                    println("uuidExtra is still null")
                }

                if (null != device.name) {
                    onBleDeviceFound.invoke(BleDevice(device, device.name, device.address, rssi))
                }


            }
        }
    }
}