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
import androidx.compose.runtime.mutableStateListOf

class DeviceListData(initialBleDevice: List<BleDevice>) {

    private val _bleDevice: MutableList<BleDevice> =
        mutableStateListOf(*initialBleDevice.toTypedArray())

    val bleDevice: List<BleDevice> = _bleDevice

    fun addMessage(msg: BleDevice) {
        _bleDevice.add(msg)

    }
}


data class BleDevice(
    var bluetoothDevice: BluetoothDevice? = null,
    val deviceName: String,
    val bleAddress: String,
    val signalValue: Int
)
