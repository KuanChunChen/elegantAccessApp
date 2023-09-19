package com.elegant.access.data

import com.elegant.access.ui.deviceList.BleDevice
import com.elegant.access.ui.deviceList.DeviceListData

private val initialBleDevice = listOf(

    BleDevice(
        null,
        "Demo001",
        "00:00:00:a1:2b:cc",
        -87
    ),
    BleDevice(
        null,
        "Demo002",
        "12:33:22:a1:2b:cc",
        12
    ),
    BleDevice(
        null,
        "Demo003",
        "13:14:1F:a1:2b:cc",
        125
    ),
    BleDevice(
        null,
        "Demo004",
        "aa:b1:c5:a1:2b:cc",
        18
    ),
    BleDevice(
        null,
        "Demo005",
        "aa:dd:22:a1:2b:cc",
        -12
    ),
    BleDevice(
        null,
        "Demo006",
        "aa:33:22:a1:2b:cc",
        2
    ),
    BleDevice(
        null,
        "Demo007",
        "cc:33:22:a1:2b:cc",
        1
    ),
    BleDevice(
        null,
        "Demo008",
        "12:ab:22:a1:2b:cc",
        33
    ),
    BleDevice(
        null,
        "Demo009",
        "12:33:b2:a1:2b:cc",
        -4
    ),
    BleDevice(
        null,
        "Demo010",
        "12:33:22:11:2b:cc",
        2
    ),
    BleDevice(
        null,
        "Demo011",
        "cc:33:32:dd:2b:cc",
        55
    ),
    BleDevice(
        null,
        "Demo012",
        "ac:ba:ff:11:2b:cc",
        41
    ),
    BleDevice(
        null,
        "Demo013",
        "ad:da:fa:11:2b:cc",
        32
    ),
    BleDevice(
        null,
        "Demo014",
        "ab:cd:22:11:2b:cc",
        12
    )
)


private val secondTestData = listOf(

    BleDevice(
        null,
        "Demo303",
        "10:20:30:a1:2b:cc",
        -17
    )
)

val exampleDeviceListData = DeviceListData(initialBleDevice)

fun getBleList(): List<BleDevice> {
    return initialBleDevice
}

fun getSecondBleList(): List<BleDevice> {
    return secondTestData
}

