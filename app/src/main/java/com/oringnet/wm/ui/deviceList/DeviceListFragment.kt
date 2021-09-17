package com.oringnet.wm.ui.deviceList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ViewWindowInsetObserver
import com.oringnet.wm.R
import com.oringnet.wm.data.exampleDeviceListData
import com.oringnet.wm.ui.WeidumllerAppTheme

val LocalBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcher> { error("No Back Dispatcher provided") }


class DeviceListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {

        val windowInsets = ViewWindowInsetObserver(this)
            .start(windowInsetsAnimationsEnabled = true)

        setContent {
//            CompositionLocalProvider(
//                LocalBackPressedDispatcher provides requireActivity().onBackPressedDispatcher,
//                LocalWindowInsets provides windowInsets)
//            {
                WeidumllerAppTheme() {
                    DeviceListContent(exampleDeviceListData){

                        val bundle = bundleOf("DeviceName" to it.deviceName)
                        NavHostFragment
                            .findNavController(this@DeviceListFragment)
                            .navigate(R.id.toLoginPage, bundle)

                    }
                }
//            }
        }
    }
}




