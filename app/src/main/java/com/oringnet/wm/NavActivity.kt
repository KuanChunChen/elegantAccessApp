package com.oringnet.wm

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.oringnet.wm.base.BaseActivity

class NavActivity : BaseActivity() {
    override val contentViewLayout: Int
        get() = R.layout.activity_nav_main

    override fun initView() {
        setStatusBarColor(this, isTranslate = true, isDarkText = true,
            backgroundColor = R.color.ea_yellow
        )
    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController().navigateUp() || super.onSupportNavigateUp()
    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHostFragment.navController
    }
}