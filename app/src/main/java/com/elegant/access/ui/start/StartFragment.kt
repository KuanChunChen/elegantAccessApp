package com.elegant.access.ui.start

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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ViewWindowInsetObserver
import com.google.accompanist.insets.navigationBarsPadding
import com.elegant.access.R
import com.elegant.access.base.util.hasBluetoothPermissions
import com.elegant.access.base.util.requireBluetoothPermissions
import timber.log.Timber

class StartFragment : Fragment() {
    @ExperimentalAnimationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        val windowInsets = ViewWindowInsetObserver(this)
            .start(windowInsetsAnimationsEnabled = true)

        val requestMultiplePermissionsLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
                Timber.d("requestMultiplePermissionsLauncher permissions:${permissions}")

                if (permissions.all { it.value }) {
                    NavHostFragment
                        .findNavController(this@StartFragment)
                        .navigate(R.id.toDeviceListPage)
                } else {
                    Toast.makeText(
                        context,
                        "Ble permission miss, please go to setting to open it.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        setContent {
            CompositionLocalProvider(
                LocalWindowInsets provides windowInsets,
            ) {
                StartContent(modifier = Modifier.navigationBarsPadding(bottom = true)) {

                    Timber.d("hasPermission:${requireContext().hasBluetoothPermissions()}")
                    if (!requireContext().hasBluetoothPermissions()) {
                        requestMultiplePermissionsLauncher.requireBluetoothPermissions()
                    } else {
                        NavHostFragment
                            .findNavController(this@StartFragment)
                            .navigate(R.id.toDeviceListPage)
                    }
                }
            }
        }
    }


}

