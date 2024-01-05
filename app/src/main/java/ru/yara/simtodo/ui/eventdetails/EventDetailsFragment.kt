package ru.yara.simtodo.ui.eventdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.yara.simtodo.databinding.FragmentEventDetailsBinding
import ru.yara.simtodo.domain.model.Event
import java.time.format.DateTimeFormatter

class EventDetailsFragment : Fragment() {

    private var _binding: FragmentEventDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val event = arguments?.getParcelable("event") as Event?
        event?.let {
            (activity as? AppCompatActivity)?.supportActionBar?.title = event.name
            binding.tvEventDTStart.text = event.dateStart.format(formatter)
            binding.tvEventDTEnd.text = event.dateFinish.format(formatter)
            binding.tvEventTitle.text = event.name
            binding.tvEventDesc.text = event.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}