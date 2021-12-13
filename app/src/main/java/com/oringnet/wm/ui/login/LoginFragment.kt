package com.oringnet.wm.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.google.accompanist.insets.ViewWindowInsetObserver
import com.oringnet.wm.ui.WeidumllerAppTheme

class LoginFragment  :  Fragment() {
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
                WeidumllerAppTheme {

                    val deviceName:String = arguments?.getString("DeviceName").toString()

                    when (0) {
                        0->{
                            //login
                            LoginPageContent(deviceName = deviceName,
                                isHavePassword = true,
                                onCancel = { NavHostFragment.findNavController(this@LoginFragment).popBackStack() },
                                onConfirm = {
                                    checkPassword()
                                }
                            )
                        }
                        1->{
                            //init
                            LoginPageContent(deviceName = deviceName,
                                isHavePassword = false,
                                onCancel = { NavHostFragment.findNavController(this@LoginFragment).popBackStack() },
                                onConfirm = {
                                    writeInitPassword()
                                })


                        }
                        2->{
                            SetNewDeviceContent(onCancel = {
                                NavHostFragment.findNavController(this@LoginFragment).popBackStack()
                            })
                        }

                    }
//                }
            }
        }
    }

    private fun checkPassword() {
        //TODO check password logic
        val checkPassword = true

        if (checkPassword) {
            NavHostFragment.findNavController(this@LoginFragment).popBackStack()
        } else {
            Toast.makeText(context, "The password you entered is incorrect.", Toast.LENGTH_SHORT).show()

        }
    }

    private fun writeInitPassword() {
        //TODO write init password logic
        val checkPassword = true
        if (checkPassword) {
            NavHostFragment.findNavController(this@LoginFragment).popBackStack()
        } else {
            Toast.makeText(context, "The password you entered is incorrect.", Toast.LENGTH_SHORT).show()

        }
    }
}

