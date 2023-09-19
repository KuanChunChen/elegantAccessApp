package com.elegant.access.ui.deviceList

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