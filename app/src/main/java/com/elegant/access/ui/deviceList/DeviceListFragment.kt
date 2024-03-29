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

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.insets.ViewWindowInsetObserver
import com.elegant.access.R
import com.elegant.access.base.constant.BluetoothConstant.REQUEST_ENABLE_BT
import com.elegant.access.base.extension.requestMultiplePermissions
import com.elegant.access.service.BluetoothLeService
import com.elegant.access.ui.theme.ElegantAccessAppTheme
import kotlinx.coroutines.*
import timber.log.Timber

class DeviceListFragment : Fragment() {

    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private val deviceViewModel: DeviceViewModel by activityViewModels()
    private var bluetoothLeService: BluetoothLeService? = null
    private var currentChooseDevice: String? = null

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

                ElegantAccessAppTheme {
                    DeviceListContent(
                        deviceViewModel = deviceViewModel,
                        navigateToLogin = {
                            scanBleDevice(false)

//                            MainScope().launch(Dispatchers.IO) {
////                                ConnectThread(it.bluetoothDevice!!).start()
//                            }

                            Timber.e("navigateToLogin")
                            currentChooseDevice = it.bleAddress
                            val gattServiceIntent = Intent(context, BluetoothLeService::class.java)
                            requireActivity().bindService(gattServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
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





    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(componentName: ComponentName, service: IBinder) {
            bluetoothLeService = (service as BluetoothLeService.LocalBinder).service
            if (!bluetoothLeService!!.initialize()) {
                Timber.e("Unable to initialize Bluetooth")
//                finish()
                return
            }
            Timber.e("Good!")
            bluetoothLeService!!.connect(currentChooseDevice)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Timber.e("disconnect!")
            bluetoothLeService = null
        }
    }


    private fun scanBleDevice(isEnable: Boolean) {
        if (isEnable) {
            deviceViewModel.isRefreshing.value = true
            deviceViewModel.viewModelScope.launch {
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




