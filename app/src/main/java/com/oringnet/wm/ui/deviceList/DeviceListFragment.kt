package com.oringnet.wm.ui.deviceList

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.insets.ViewWindowInsetObserver
import com.oringnet.wm.R
import com.oringnet.wm.base.constant.BluetoothConstant.REQUEST_ENABLE_BT
import com.oringnet.wm.base.extension.requestMultiplePermissions
import com.oringnet.wm.ui.WeidumllerAppTheme
import kotlinx.coroutines.*
import timber.log.Timber

class DeviceListFragment : Fragment() {

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val deviceViewModel: DeviceViewModel by activityViewModels()
    private val receiver = DeviceListBoardCast { bleDevice ->
        Timber.d(bleDevice.toString())
        deviceViewModel.addDevice(bleDevice)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestMultiplePermissions(Manifest.permission.ACCESS_FINE_LOCATION, allGranted = {
                Log.d("test:", "2 Ask permission success.")


            },
            denied = {
                Log.d("test:", "3 Ask permission success.")
            },
            explained = {
                Log.d("test:", "4 Ask permission success.")
            }
        )




    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        val windowInsets = ViewWindowInsetObserver(this)
            .start(windowInsetsAnimationsEnabled = true)


        setContent {

                WeidumllerAppTheme {
                    DeviceListContent(
                        deviceViewModel = deviceViewModel,
                        navigateToLogin = {

                            val bundle = bundleOf("DeviceName" to it.deviceName)
                            NavHostFragment
                                .findNavController(this@DeviceListFragment)
                                .navigate(R.id.toLoginPage, bundle)
                        },
                        onSwipeRefresh = {
                            scanBleDevice(false)
                            scanBleDevice(true)
                        }

                    )
                }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("test:", bluetoothAdapter?.isEnabled.toString())

        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
        }

        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        pairedDevices?.forEach { device ->
            val deviceName = device.name
            val deviceHardwareAddress = device.address // MAC address

        }

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        requireContext().registerReceiver(receiver, filter)

        scanBleDevice(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(receiver)
        bluetoothAdapter!!.cancelDiscovery()
    }


    private fun scanBleDevice(isEnable: Boolean) {
        if (isEnable) {
            deviceViewModel.isRefreshing.value = true
            MainScope().launch {
                if (deviceViewModel.isRefreshing.value == true) {
                    delay(6000)
                    bluetoothAdapter!!.cancelDiscovery()
                    deviceViewModel.isRefreshing.value = false
                }
            }
            deviceViewModel.clearDevices()
            bluetoothAdapter!!.startDiscovery()

        } else {
            deviceViewModel.isRefreshing.value = false
            bluetoothAdapter!!.cancelDiscovery()
        }

    }
}




