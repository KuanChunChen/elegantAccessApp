package com.oringnet.wm.base

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import com.oringnet.wm.R
import com.oringnet.wm.base.util.StatusBarUtil

abstract class BaseActivity :TransitionActivity() {

    @get:LayoutRes
    protected abstract val contentViewLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentViewLayout)

        setStatusBarColor(this, isTranslate = true, isDarkText = true,
                backgroundColor = R.color.white
        )

        initView()

        supportActionBar?.hide()
    }


    protected abstract fun initView()


    /**
     * set the status bar color
     * */


    open fun setStatusBarColor(
            activity: Activity,
            isTranslate: Boolean,
            isDarkText: Boolean, @ColorRes backgroundColor: Int
    ) {


        //api 21 up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(
                    activity,
                    backgroundColor
            )
        }
        //api 23 up
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }




    }

    protected fun setStatusBarColor(
            activity: Activity?,
            bgColor: Int
    ) {
        StatusBarUtil.setStatusBarColor(activity,bgColor)
    }



    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view: View? = currentFocus
            if (view is EditText) {
                val outRect = Rect()
                view.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    view.clearFocus()
                    val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}