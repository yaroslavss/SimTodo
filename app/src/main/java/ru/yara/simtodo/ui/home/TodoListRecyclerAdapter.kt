package ru.yara.simtodo.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yara.simtodo.databinding.ItemTodoBinding
import ru.yara.simtodo.domain.model.Hour

class TodoListRecyclerAdapter(
    var hours: List<Hour>,
    private val onItemClick: (hour: Hour) -> Unit
) : RecyclerView.Adapter<TodoListRecyclerAdapter.TodoViewHolder>() {

    private var _binding: ItemTodoBinding? = null
    private val binding get() = _binding!!

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        _binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TodoViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return hours.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = hours[position]
        holder.itemView.apply {
            binding.tvHourLabel.text = item.startHour + "-" + item.endHour
        }

        binding.root.setOnClickListener {
            onItemClick(hours[position])
        }
    }
}