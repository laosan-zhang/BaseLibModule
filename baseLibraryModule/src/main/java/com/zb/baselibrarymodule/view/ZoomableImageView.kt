package com.zb.baselibrarymodule.view

import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

/**
 *  author : 86175
 *  time   : 2025/5/27 13:42
 *  com.zb.baselibmodule
 *  图片预览组件，可缩放
 */


class ZoomableImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    // 手势检测
    private val scaleDetector = ScaleGestureDetector(context, ScaleListener())
    private val matrix = Matrix()
    private val initialScale = PointF()
    private var lastTouch = PointF()
    private var minScale = 1f
    private var maxScale = 5f

    init {
        // 确保图片缩放模式正确
        scaleType = ScaleType.MATRIX
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // 图片加载完成后初始化缩放参数
        post {
            setupScaleParams()
        }
    }

    private fun setupScaleParams() {
        drawable?.let { drawable ->
            // 计算初始缩放比例（适应View大小）
            val widthRatio = width.toFloat() / drawable.intrinsicWidth
            val heightRatio = height.toFloat() / drawable.intrinsicHeight
            minScale = minOf(widthRatio, heightRatio)
            maxScale = minScale * 5

            // 居中显示
            matrix.setScale(minScale, minScale)
            matrix.postTranslate(
                (width - drawable.intrinsicWidth * minScale) / 2,
                (height - drawable.intrinsicHeight * minScale) / 2
            )
            imageMatrix = matrix
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastTouch.set(event.x, event.y)
            }

            MotionEvent.ACTION_MOVE -> {
                if (!scaleDetector.isInProgress) {
                    // 平移处理
                    val dx = event.x - lastTouch.x
                    val dy = event.y - lastTouch.y
                    matrix.postTranslate(dx, dy)
                    constrainTranslation()
                    imageMatrix = matrix
                    lastTouch.set(event.x, event.y)
                }
            }
        }
        return true
    }

    private fun constrainTranslation() {
        // 边界检查（防止图片移出视图）
        val rect = getDrawableRect()
        val viewWidth = width.toFloat()
        val viewHeight = height.toFloat()

        var deltaX = 0f
        var deltaY = 0f

        if (rect.width() <= viewWidth) {
            deltaX = viewWidth / 2 - rect.centerX()
        } else {
            if (rect.left > 0) deltaX = -rect.left
            if (rect.right < viewWidth) deltaX = viewWidth - rect.right
        }

        if (rect.height() <= viewHeight) {
            deltaY = viewHeight / 2 - rect.centerY()
        } else {
            if (rect.top > 0) deltaY = -rect.top
            if (rect.bottom < viewHeight) deltaY = viewHeight - rect.bottom
        }

        matrix.postTranslate(deltaX, deltaY)
    }

    private fun getDrawableRect(): RectF {
        val rect = RectF()
        drawable?.let {
            rect.set(0f, 0f, it.intrinsicWidth.toFloat(), it.intrinsicHeight.toFloat())
            matrix.mapRect(rect)
        }
        return rect
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor
            val currentScale = getCurrentScale()

            // 限制缩放范围
            val newScale = currentScale * scaleFactor
            if (newScale in minScale..maxScale) {
                matrix.postScale(
                    scaleFactor,
                    scaleFactor,
                    detector.focusX,
                    detector.focusY
                )
                constrainTranslation()
                imageMatrix = matrix
            }
            return true
        }
    }

    private fun getCurrentScale(): Float {
        val values = FloatArray(9)
        matrix.getValues(values)
        return values[Matrix.MSCALE_X]
    }

    // 双击恢复初始状态
    override fun performClick(): Boolean {
        setupScaleParams()
        return super.performClick()
    }
}