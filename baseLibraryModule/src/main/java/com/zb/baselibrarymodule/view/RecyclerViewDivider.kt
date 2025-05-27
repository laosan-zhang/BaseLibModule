package com.zb.baselibrarymodule.view

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *  author : 86175
 *  time   : 2025/5/23 15:06
 *  com.zb.baselibrarymodule.view
 */
private abstract class BaseDivider(
    protected val thickness: Any,
    var showLastItem: Boolean = false
) : RecyclerView.ItemDecoration() {

    protected fun getThicknessPx(recyclerView: RecyclerView): Int {
        return when (thickness) {
            is Int -> thickness
            is Float -> thickness.toInt()
            else -> recyclerView.resources.getDimensionPixelSize(thickness as Int)
        }
    }

    protected fun shouldDrawDivider(
        parent: RecyclerView,
        child: View,
        position: Int
    ): Boolean {
        return position < parent.adapter!!.itemCount - 1 || showLastItem
    }
}

// 纯色分割线
private class ColorDivider(
    @ColorInt private val color: Int,
    thickness: Any
) : BaseDivider(thickness) {

    private val paint = Paint().apply {
        this.color = this@ColorDivider.color
        style = Paint.Style.FILL
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val thicknessPx = getThicknessPx(parent)
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (shouldDrawDivider(parent, child, position)) {
                drawDivider(c, parent, child, thicknessPx)
            }
        }
    }

    private fun drawDivider(
        c: Canvas,
        parent: RecyclerView,
        child: View,
        thicknessPx: Int
    ) {
        val position = parent.getChildAdapterPosition(child)
        if (position == RecyclerView.NO_POSITION) return
        val params = child.layoutParams as RecyclerView.LayoutParams
        when (parent.layoutManager) {
            is LinearLayoutManager -> {
                val orientation = (parent.layoutManager as LinearLayoutManager).orientation
                if (orientation == RecyclerView.VERTICAL) {
                    // 垂直列表：底部画线
                    val top = child.bottom + params.bottomMargin
                    c.drawRect(
                        child.left.toFloat(),
                        top.toFloat(),
                        child.right.toFloat(),
                        0.5f+(top + thicknessPx).toFloat(),
                        paint
                    )
                } else {
                    // 水平列表：右侧画线
                    val left = child.right + params.rightMargin
                    c.drawRect(
                        left.toFloat(),
                        child.top.toFloat(),
                        0.5f+(left + thicknessPx).toFloat(),
                        child.bottom.toFloat(),
                        paint
                    )
                }
            }

        }
    }
}

// 自定义图形分割线
private class DrawableDivider(
    private val drawable: Drawable,
    thickness: Any
) : BaseDivider(thickness) {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val thicknessPx = getThicknessPx(parent)
        drawable.setBounds(0, 0, parent.width, thicknessPx)
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(child)
            if (shouldDrawDivider(parent, child, position)) {
                drawDivider(c, parent, child, thicknessPx)
            }
        }
    }

    private fun drawDivider(
        c: Canvas,
        parent: RecyclerView,
        child: View,
        thicknessPx: Int
    ) {
        val params = child.layoutParams as RecyclerView.LayoutParams
        when (parent.layoutManager) {
            is LinearLayoutManager -> {
                val orientation = (parent.layoutManager as LinearLayoutManager).orientation
                if (orientation == RecyclerView.VERTICAL) {
                    // 垂直列表
                    val top = child.bottom + params.bottomMargin
                    drawable.setBounds(
                        child.left,
                        top,
                        child.right,
                        top + thicknessPx
                    )
                } else {
                    // 水平列表
                    val left = child.right + params.rightMargin
                    drawable.setBounds(
                        left,
                        child.top,
                        left + thicknessPx,
                        child.bottom
                    )
                }
                drawable.draw(c)
            }
        }
    }
}

/**
 * 添加分割线（支持纯色或自定义 Drawable）
 * @param color 分割线颜色（设为 0 时使用 drawable）
 * @param thickness 分割线厚度（像素或资源ID）
 * @param drawable 自定义分割线图形（可选）
 * @param showLastItem 是否在最后一项下方显示分割线
 */
fun RecyclerView.addDivider(
    @ColorInt color: Int = 0,
    thickness: Any = 1, // 支持像素值或资源ID
    drawable: Drawable? = null,
    showLastItem: Boolean = false
) {
    val divider = when {
        color != 0 -> ColorDivider(color, thickness)
        drawable != null -> DrawableDivider(drawable, thickness)
        else -> throw IllegalArgumentException("必须指定 color 或 drawable")
    }.apply {
        this.showLastItem = showLastItem
    }

    addItemDecoration(divider)
}

// 快捷方法：通过资源ID添加
fun RecyclerView.addDivider(
    @DrawableRes drawableRes: Int,
    @DimenRes thicknessRes: Int? = null,
    showLastItem: Boolean = false
) {
    val drawable = ContextCompat.getDrawable(context, drawableRes)!!
    val thickness = thicknessRes?.let { resources.getDimensionPixelSize(it) } ?: drawable.intrinsicHeight
    addDivider(drawable = drawable, thickness = thickness, showLastItem = showLastItem)
}