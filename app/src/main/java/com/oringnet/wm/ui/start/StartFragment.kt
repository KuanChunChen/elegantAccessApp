package com.oringnet.wm.ui.start

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
import com.oringnet.wm.R
import com.oringnet.wm.base.util.hasBluetoothPermissions
import com.oringnet.wm.base.util.requireBluetoothPermissions
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

