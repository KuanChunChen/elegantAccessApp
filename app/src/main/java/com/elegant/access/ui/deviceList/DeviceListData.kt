package com.elegant.access.ui.deviceList

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
