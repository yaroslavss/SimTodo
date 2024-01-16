package ru.yara.simtodo.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.yara.simtodo.R
import ru.yara.simtodo.databinding.FragmentHomeBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // get initial data
        val allEventList = viewModel.getAllEventsFromJson(context)
        viewModel.saveEventsIntoDb(allEventList)
    }

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

        // set date on calendar view and get events on start
        setDate()

        // set recycler view adapter
        val adapter = TodoListRecyclerAdapter(listOf())
        binding.rvTodoList.adapter = adapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(activity)

        // proceed date change on CalendarView
        binding.cvCalendar.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val day = LocalDate.of(year, (month + 1), dayOfMonth)
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    viewModel.getEventsForDay(day)
                }
            }
            viewModel.currentDate = day
        }

        // add new event
        val navController = findNavController()
        binding.fab.setOnClickListener {
            navController.navigate(R.id.addEventFragment)
        }

        // observe hours with events livedata
        viewModel.hourListLiveData.observe(viewLifecycleOwner) { list ->
            adapter.hours = list
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDate() {
        var currentDate = viewModel.currentDate
        val zoneOffset = ZoneOffset.systemDefault().getRules().getOffset(LocalDateTime.now())

        if (currentDate != null) {
            binding.cvCalendar.date = currentDate.atStartOfDay().toEpochSecond(zoneOffset) * 1000
        } else {
            currentDate = LocalDate.now()
            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    viewModel.getEventsForDay(currentDate)
                }
            }
            viewModel.currentDate = currentDate
        }
    }
}