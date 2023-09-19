package com.elegant.access.base.widget.seekbar

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
