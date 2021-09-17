package com.oringnet.wm.base

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.oringnet.wm.R

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
