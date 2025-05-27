package com.zb.baselibrarymodule.Base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.google.gson.Gson
import com.zb.baselibrarymodule.Utils.DenistyUtils.Companion.dpToPx
import java.lang.reflect.ParameterizedType
import kotlin.math.max

/**
 * Author by zhangbing
 * Time is 2025/5/15 17:51
 * 弹窗基础类
 */
abstract class BaseDialog<T : ViewBinding, R>(
    private val dialogContext: Context,
    var data: R?,
    themeResId: Int
) : Dialog(dialogContext, themeResId) {
    lateinit var binding: T
    private var defaultWidth: Int
    private var defaultHeight: Int
    private var hasPadding = true
    private var paddings: Int

    constructor(context: Context, themeResId: Int) : this(context, null, themeResId)

    init {
        defaultWidth = dpToPx(dialogContext, 400f)
        defaultHeight = dpToPx(dialogContext, 300f)
        paddings = dpToPx(dialogContext, 15f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置弹窗不会自动取消 若有需要，可以在子类初始化后重新设置这两个参数
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        val type = this.javaClass.genericSuperclass
        if (type is ParameterizedType) {
            try {
                val clazz = type.actualTypeArguments[0] as Class<*>
                val method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
                binding = method.invoke(null, layoutInflater) as T
                setContentView(binding.root)
            } catch (e: Exception) {
                throw IllegalStateException("ViewBinding 获取失败！")
            }
        }
        initView()
        initData()
        initLinstener()
    }

    /**
     * 获取上下文
     */
    fun getDialogContext():Context = dialogContext

    /**
     * 修改 dialog 弹窗 宽高信息
     *
     * @param width
     * @param height
     */
    @JvmOverloads
    fun updateDefaultWidth(width: Int, height: Int, isReset: Boolean = true) {
        var newWidth = width
        var newHeight = height
        if (newWidth == 0) {
            newWidth = defaultWidth
        }
        if (newHeight == 0) {
            newHeight = defaultHeight
        }
        defaultWidth = newWidth
        defaultHeight = newHeight
        if (isReset) resetScreenSize()
    }

    /**
     * 设置
     * @param hasPadding dialog是否需要两侧间距
     * @param paddings   间距大小
     * @return
     */
    fun hasPadding(hasPadding: Boolean, paddings: Int) {
        this.hasPadding = hasPadding
        this.paddings = max(paddings.toDouble(), 0.0).toInt()
        resetScreenSize()
    }

    /**
     * 设置dialog两侧间距
     */
    fun paddingSize(padding: Int) {
        this.paddings = padding
        resetScreenSize()
    }

    /**
     * 设置dialog 显示尺寸
     */
    fun resetScreenSize() {
        val dialogWindow = window
        val lp = dialogWindow!!.attributes
        if (defaultWidth == ViewGroup.LayoutParams.MATCH_PARENT && hasPadding) {
            dialogWindow.decorView.setPadding(paddings, 0, paddings, 0)
        }
        lp.width = defaultWidth //设置为屏幕宽度
        lp.height = defaultHeight //设置为屏幕高度
        dialogWindow.attributes = lp
    }


    /**
     * 需要做View渲染的重写这个方法
     */
    open fun initView() {
    }

    /**
     * 需要做数据加载的重写这个方法
     */
    open fun initData() {
    }

    /**
     * 需要做事件监听的重写这个方法
     */
    abstract fun initLinstener()
}
