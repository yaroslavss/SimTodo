package ru.yara.simtodo.ui.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.yara.simtodo.R
import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.databinding.FragmentAddEventBinding
import java.time.LocalDate

class AddEventFragment : Fragment() {

    private var _binding: FragmentAddEventBinding? = null
    private val viewModel by lazy {
        ViewModelProvider(this).get(AddEventViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // add event
        val navController = findNavController()
        binding.btnAddEvent.setOnClickListener {
            val title = binding.tietEventTitle.text.toString()
            val dtStart = kotlin.runCatching {
                LocalDate.parse(binding.tietEventDTStart.text.toString()).atStartOfDay()
            }.getOrNull()
            val dtEnd = kotlin.runCatching {
                LocalDate.parse(binding.tietEventDTEnd.text.toString()).atStartOfDay().plusHours(1)
            }.getOrNull()
            val desc = binding.tietEventDesc.text.toString()

            if (title.isEmpty() || (dtStart == null) || (dtEnd == null) || desc.isEmpty()) {
                Toast.makeText(
                    activity,
                    R.string.msg_empty_fields,
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            viewModel.addEventIntoDB(
                EventEntity(
                    id = 0,
                    dateStart = dtStart,
                    dateFinish = dtEnd,
                    name = title,
                    description = desc
                )
            )

            navController.navigate(R.id.homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}