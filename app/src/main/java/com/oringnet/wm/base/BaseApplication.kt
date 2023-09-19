package com.oringnet.wm.base

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

open class BaseApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)



        if (BuildConfig.DEBUG) {
        }
    }

}