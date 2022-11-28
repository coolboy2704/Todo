package com.example.todolist.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.task.Data
import com.example.todolist.databinding.ItemTasksBinding


class TaskAdapter(private val listener: onItemClickListener) : RecyclerView.Adapter<TaskAdapter.VH>() {
    var model: MutableList<Data> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class VH(val binding: ItemTasksBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun getItemCount(): Int = model.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tasks, parent, false)
        val binding = ItemTasksBinding.bind(view)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val task = model[position]

        holder.binding.apply {
            checkbox.isChecked = task.is_done
            checkbox.isEnabled != task.is_done
            tvTaskPermission.text = task.description
            tvTaskPermission.paint.isStrikeThruText = task.is_done

            delete.setOnClickListener {
                onClick.invoke(task,model.indexOf(task))
            }

            checkbox.setOnClickListener {
                checkboxClick.invoke(task)
            }

            root.setOnClickListener {
                listener.onItemClick(task)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(task: Data)
//        fun onCheckBoxClick(task: Data, isChecked:  )
    }

    private var checkboxClick: (data: Data) -> Unit ={}
    fun setOnCheckbox(checkboxClick : (data: Data) -> Unit) {
        this.checkboxClick = checkboxClick
    }

    private var onClick: (data: Data, position: Int) -> Unit = { text, position -> }
    fun removeItemClick(itemClick: (data: Data, position: Int) -> Unit) {
        this.onClick = itemClick
    }

    fun removeTask(position: Int) {
        if (model.size > 0){
            model.removeAt(position)
            notifyItemRemoved(position)
        }
    }

}
