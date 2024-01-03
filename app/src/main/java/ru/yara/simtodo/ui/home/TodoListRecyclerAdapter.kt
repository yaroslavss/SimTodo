package ru.yara.simtodo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import ru.yara.simtodo.R
import ru.yara.simtodo.databinding.ItemTodoBinding
import ru.yara.simtodo.domain.model.Hour
import java.time.format.DateTimeFormatter

class TodoListRecyclerAdapter(
    var hours: List<Hour>
) : RecyclerView.Adapter<TodoListRecyclerAdapter.TodoViewHolder>() {

    private var _binding: ItemTodoBinding? = null
    private val binding get() = _binding!!
    private val formatter = DateTimeFormatter.ofPattern("HH:mm")

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
        val context = holder.itemView.context
        val bundle = Bundle()

        holder.itemView.apply {
            binding.tvHourLabel.text =
                item.startHour.format(formatter) + " - " + item.endHour.format(formatter)

            // add events for this hour
            for (event in item.eventList) {
                val eventTextView = TextView(context).apply {
                    id = event.id
                    text = event.dateStart.format(formatter) + " - " + event.dateFinish.format(
                        formatter
                    ) + ", " + event.name
                    setOnClickListener {
                        bundle.putParcelable("event", event)
                        it.findNavController().navigate(R.id.eventDetailsFragment, bundle)
                    }
                }
                binding.llEventList.addView(eventTextView)
            }
        }

        holder.setIsRecyclable(false)
    }
}