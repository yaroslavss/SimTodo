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
import ru.yara.simtodo.utils.DateTimePicker
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

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

        val format = SimpleDateFormat(DATE_TIME_FORMAT, Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY))
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)

        // select date and time of event's start
        binding.tilEventDTStart.setStartIconOnClickListener() {
            DateTimePicker(requireContext()) { dateTimeInMillis ->
                binding.tietEventDTStart.setText(format.format(Date(dateTimeInMillis)).toString())
            }
        }

        // select date and time of event's end
        binding.tilEventDTEnd.setStartIconOnClickListener {
            DateTimePicker(requireContext()) { dateTimeInMillis ->
                binding.tietEventDTEnd.setText(format.format(Date(dateTimeInMillis)).toString())
            }
        }

        // add event
        val navController = findNavController()

        binding.btnAddEvent.setOnClickListener {
            val title = binding.tietEventTitle.text.toString()
            val dtStart = kotlin.runCatching {
                LocalDateTime.parse(binding.tietEventDTStart.text.toString(), formatter)
            }.getOrNull()
            val dtEnd = kotlin.runCatching {
                LocalDateTime.parse(binding.tietEventDTEnd.text.toString(), formatter)
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

    companion object {
        const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"
        const val LOCALE_LANGUAGE = "ru"
        const val LOCALE_COUNTRY = "RU"
    }
}