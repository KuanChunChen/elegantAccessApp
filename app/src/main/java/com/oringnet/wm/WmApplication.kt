package com.oringnet.wm

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.app.ActivityCompat.startActivityForResult
import com.oringnet.wm.base.BaseApplication
import timber.log.Timber


class WmApplication : BaseApplication() {

    override fun onCreate() {

        super.onCreate()
        mContext = this
        Timber.plant(Timber.DebugTree())



    }

    private fun requestPermissions(arrayOf: Array<String>, requestFineLocationPermission: Array<String>) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            /**確認是否已開啟取得手機位置功能以及權限 */
//            val hasGone = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//            if (hasGone != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
//                        Manifest.permission.REQUEST_FINE_LOCATION_PERMISSION)
//            }
//            /**確認手機是否支援藍牙BLE */
//            if (!packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
//                Toast.makeText(this, "Not support Bluetooth", Toast.LENGTH_SHORT).show()
//                finish()
//            }
//            /**開啟藍芽適配器 */
//            if (!mBluetoothAdapter.isEnabled()) {
//                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
//            }
//        } else finish()
    }

    companion object {
        private lateinit var mContext: Context

        fun getContext(): Context {
            return mContext
        }
    }
}