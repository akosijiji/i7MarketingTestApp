package com.i7marketingtestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.i7marketingtestapp.model.Event
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class EventsRVAdapter : ListAdapter<Event, EventsRVAdapter.EventHolder>(DiffCallback()) {

    class EventHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var listener: RecyclerClickListener
    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.events_row, parent, false)
        val noteHolder = EventHolder(v)

//        val event = noteHolder.itemView.findViewById<CardView>(R.id.event)
//        event.setOnClickListener {
//            listener.onItemClick(noteHolder.adapterPosition)
//        }

        return noteHolder
    }

    override fun onBindViewHolder(holder: EventHolder, position: Int) {
        val currentItem = getItem(position)
        val eventText = holder.itemView.findViewById<TextView>(R.id.event_text)
        val eventDate = holder.itemView.findViewById<TextView>(R.id.event_date)
        eventText.text = currentItem.eventText
        val dateFormatted = SimpleDateFormat("dd/MM/yyyy hh:mm a").format(currentItem.dateAdded)
        eventDate.text = dateFormatted
    }

    class DiffCallback : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) =
            oldItem.dateAdded == newItem.dateAdded

        override fun areContentsTheSame(oldItem: Event, newItem: Event) =
            oldItem == newItem
    }
}