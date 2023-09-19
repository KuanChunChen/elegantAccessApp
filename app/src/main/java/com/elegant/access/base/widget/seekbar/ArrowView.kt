package com.elegant.access.base.widget.seekbar

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

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class ArrowView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private val mWidth: Int = SizeUtils.dp2px(context, 12f)
    private val mHeight: Int = SizeUtils.dp2px(context, 7f)
    private val mPath: Path = Path()
    private val mPaint: Paint
    fun setColor(color: Int) {
        mPaint.color = color
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(mPath, mPaint)
    }

    init {
        mPath.moveTo(0f, 0f)
        mPath.lineTo(mWidth.toFloat(), 0f)
        mPath.lineTo(mWidth / 2f, mHeight.toFloat())
        mPath.close()
        mPaint = Paint()
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 1f
    }
}
