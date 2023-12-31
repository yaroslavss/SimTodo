package ru.yara.simtodo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.yara.simtodo.R
import ru.yara.simtodo.databinding.FragmentHomeBinding
import java.time.LocalDate

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = view.findNavController()
        val adapter = TodoListRecyclerAdapter(listOf()) { hour ->
            navController.navigate(R.id.eventDetailsFragment)
        }

        binding.rvTodoList.adapter = adapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(activity)

        // proceed date change on CalendarView
        binding.cvCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            viewModel.getEventsForDay(LocalDate.of(year, (month + 1), dayOfMonth))
        }

        // set event list
        viewModel.hourListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.hours = list
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}