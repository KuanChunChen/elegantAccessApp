package com.oringnet.wm.ui.deviceList

import androidx.compose.runtime.mutableStateListOf

class DeviceListData(initialBleDevice: List<BleDevice>) {

    private val _bleDevice: MutableList<BleDevice> =
        mutableStateListOf(*initialBleDevice.toTypedArray())

    val bleDevice: List<BleDevice> = _bleDevice

    fun addMessage(msg: BleDevice) {
        _bleDevice.add(msg)

    }
}


data class BleDevice(val deviceName: String, val bleAddress: String, val signalValue: Int)
