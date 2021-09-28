package com.oringnet.wm.ui.deviceList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oringnet.wm.data.getBleList
import timber.log.Timber

class DeviceViewModel : ViewModel() {

    private val _devices: MutableLiveData<List<BleDevice>> = MutableLiveData<List<BleDevice>>()

    val devices: LiveData<List<BleDevice>> = _devices

    /** Add hard code test data**/
    init {
        _devices.value = getBleList()
    }

    fun onDevicesChange(listDevices: List<BleDevice>) {
        _devices.value = listDevices
    }


    override fun onCleared() {
        super.onCleared()
        Timber.d( "DeviceViewModel destroyed!")
    }
}