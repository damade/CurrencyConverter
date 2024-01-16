package com.damilola.core_android.utils.ui_providers

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import com.damilola.core_android.R
import dagger.hilt.android.scopes.ActivityScoped


@ActivityScoped
class BadgeDrawable: Drawable() {
    private lateinit var mBadgePaint: Paint
    private var mCount = ""
    private lateinit var mTextPaint: Paint
    private var mTextSize = 0f
    private val mTxtRect: Rect = Rect()
    private var mWillDraw = false
    private var num:Int = 0


    fun BadgeDrawable(paramContext: Context) {
        mTextSize = paramContext.resources.getDimension(R.dimen.badge_text_size)
        mBadgePaint = Paint()
        mBadgePaint.color = paramContext.resources.getColor(
                R.color.black)
        mBadgePaint.isAntiAlias = true
        mBadgePaint.style = Paint.Style.FILL
        mTextPaint = Paint()
        mTextPaint.color = Color.CYAN
        mTextPaint.typeface = Typeface.DEFAULT
        mTextPaint.textSize = mTextSize
        mTextPaint.isAntiAlias = true
        mTextPaint.textAlign = Paint.Align.CENTER
    }

    override fun draw(paramCanvas: Canvas) {
        if (!mWillDraw) {
            return
        }
        val localRect = bounds
        val width = (localRect.right - localRect.left).toFloat()
        val height = (localRect.bottom - localRect.top).toFloat()
        var circleRadius: Float
        circleRadius = Math.min(width, height) / 4.0f + 2.5f
        circleRadius = if (mCount.toInt() < 10) {
            Math.min(width, height) / 4.0f + 2.5f
        } else {
            Math.min(width, height) / 4.0f + 4.5f
        }
        val circleX = width - circleRadius + 6.2f
        val circleY = circleRadius - 9.5f
        paramCanvas.drawCircle(circleX, circleY, circleRadius, mBadgePaint)
        mTextPaint.getTextBounds(mCount, 0, mCount.length, mTxtRect)
        var textY = circleY + (mTxtRect.bottom - mTxtRect.top) / 2.0f
        var textX = circleX
        if (mCount.toInt() >= 10) {
            textX = textX - 1.0f
            textY = textY - 1.0f
        }
        paramCanvas.drawText(mCount, textX, textY, mTextPaint)
    }

    override fun getOpacity(): Int {
        return num
    }

    override fun setAlpha(paramInt: Int) {}

    override fun setColorFilter(paramColorFilter: ColorFilter?) {}

    fun setCount(paramInt: Int) {
        mCount = Integer.toString(paramInt)
        if (paramInt > 0) {
            mWillDraw = true
            invalidateSelf()
        }
    }

//    override fun draw(canvas: Canvas) {
//        TODO("Not yet implemented")
//    }
//
//    override fun setAlpha(alpha: Int) {
//        TODO("Not yet implemented")
//    }
//
//    override fun getOpacity(): Int {
//        TODO("Not yet implemented")
//    }
//
//    override fun setColorFilter(colorFilter: ColorFilter?) {
//        TODO("Not yet implemented")
//    }
}