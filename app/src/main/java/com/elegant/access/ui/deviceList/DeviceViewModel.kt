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

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elegant.access.data.getBleList
import timber.log.Timber

class DeviceViewModel : ViewModel() {

    private val _devices: MutableLiveData<List<BleDevice>> = MutableLiveData<List<BleDevice>>()

    var isRefreshing: MutableLiveData<Boolean> = MutableLiveData(false)

    val devices: LiveData<List<BleDevice>> = _devices

    /** Add hard code test data**/
    init {
        _devices.value = getBleList()
    }

    fun onDevicesChange(listDevices: List<BleDevice>) {
        _devices.value = listDevices
    }

    fun addDevice(bleDevice: BleDevice) {
        _devices.value!!.toMutableList().let {
            it.add(bleDevice)
            _devices.value = it
        }
    }

    fun clearDevices(){
        _devices.value = emptyList()
    }



    override fun onCleared() {
        super.onCleared()
        Timber.d( "DeviceViewModel destroyed!")
    }
}