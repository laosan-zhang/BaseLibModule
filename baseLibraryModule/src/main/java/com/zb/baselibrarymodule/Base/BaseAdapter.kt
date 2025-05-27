import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * 通用的 RecyclerView 适配器基类
 * @param T : ViewBinding 列表项的 ViewBinding 类型
 * @param R : Any 数据实体类型
 * @param datas 初始数据（可为空）
 */
abstract class BaseAdapter<T : ViewBinding, R : Any>(
    private var datas: List<R>? = null
) : RecyclerView.Adapter<BaseAdapter.RvHolder<T>>() {

    /**
     * 安全更新数据（避免并发问题）
     */
    fun setData(newData: List<R>) {
        datas = newData.toList() // 创建新集合
        notifyDataSetChanged()
    }

    /**
     * 获取当前数据（只读）
     */
    fun getData(): List<R>? = datas?.toList()

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder<T> {
        val binding = createBinding(LayoutInflater.from(parent.context), parent, viewType)
        return RvHolder(binding)
    }

    final override fun onBindViewHolder(holder: RvHolder<T>, position: Int) {
        val item = datas?.get(position) ?: return
        bindData(holder.binding, item)
    }

    override fun getItemCount(): Int = datas?.size ?: 0

    /**
     * 创建 ViewBinding 实例
     */
    protected abstract fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): T

    /**
     * 绑定数据到 ViewBinding
     */
    protected abstract fun bindData(binding: T, item: R)


    class RvHolder<B : ViewBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)
}