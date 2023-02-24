package com.i7marketingtestapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.i7marketingtestapp.databinding.FragmentFirstBinding
import com.i7marketingtestapp.model.Event
import kotlinx.coroutines.launch
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val eventDatabase by lazy { context?.let { EventDatabase.getDatabase(it).eventDao() } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        if (view != null) {
            val eventDateAdded = Date()
            val button = view as Button
            val eventText = button.text.toString()
            // Add the new event at the top of the list
            val newEvent = Event(0, ("$eventText pressed") ?: "", eventDateAdded)
            // Add event logging to local database
            lifecycleScope.launch {
                eventDatabase?.addEvent(newEvent)
                findNavController().navigate(R.id.action_FirstFragment_to_EventsFragment)
            }
        }
    }
}