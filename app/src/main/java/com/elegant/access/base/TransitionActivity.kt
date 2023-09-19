package com.elegant.access.base

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

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.elegant.access.R

abstract class TransitionActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        setTransitionAnimation(true)

    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
        setTransitionAnimation(true)
    }

    override fun finish() {
        super.finish()
        setTransitionAnimation(false)
    }

    fun finishWithDefaultTransition() {
        super.finish()
    }

    private fun setTransitionAnimation(newActivityIn: Boolean) {
        if (newActivityIn) {
            // 往右進來
            overridePendingTransition(
                    R.anim.push_right_in,
                    R.anim.push_right_out
            )
        } else {
            // 頁面從左邊出去
            overridePendingTransition(
                    R.anim.push_left_in,
                    R.anim.push_left_out
            )
        }
    }

}
