package com.i7marketingtestapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.i7marketingtestapp.databinding.FragmentEventsBinding
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: EventsRVAdapter
    private val eventDatabase by lazy { context?.let { EventDatabase.getDatabase(it).eventDao() } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        setRecyclerView()
        observeEvents()

        return binding.root

    }

    private fun setRecyclerView() {
        _binding?.eventsRecyclerview?.layoutManager = LinearLayoutManager(context)
        _binding?.eventsRecyclerview?.setHasFixedSize(true)
        adapter = EventsRVAdapter()
        _binding?.eventsRecyclerview?.adapter = adapter
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            eventDatabase?.getEvents()?.collect { eventsList ->
                if (eventsList.isNotEmpty()) {
                    adapter.submitList(eventsList)
                    Log.d("EventsList", "" + eventsList[0])
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}