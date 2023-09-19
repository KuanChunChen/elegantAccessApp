package com.oringnet.wm

import android.content.Context
import com.oringnet.wm.base.BaseApplication
import timber.log.Timber


class WmApplication : BaseApplication() {

    override fun onCreate() {

        super.onCreate()
        mContext = this
        Timber.plant(Timber.DebugTree())



    }

    companion object {
        private lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }
    }
}