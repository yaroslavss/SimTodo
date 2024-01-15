package ru.yara.simtodo.ui.addevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.yara.simtodo.R
import ru.yara.simtodo.data.db.EventEntity
import ru.yara.simtodo.databinding.FragmentAddEventBinding

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
            viewModel.addEventIntoDB(EventEntity())
            navController.navigate(R.id.homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}