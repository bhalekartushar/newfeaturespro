package com.thenewj.newj.ui.main.event

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.ManageFollowedEventsQuery
import com.thenewj.newj.databinding.ContentEventlistBinding

class EventListAdapter (private val eventViewModel: EventViewModel) :
RecyclerView.Adapter<EventListAdapter.ViewHolder>() {


    private var mTiltle: ManageFollowedEventsQuery.Data? = null

    class ViewHolder(val binding: ContentEventlistBinding, eventViewModel: EventViewModel) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(title: ManageFollowedEventsQuery.ManageFollowedEvent?) {
            binding.title = title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContentEventlistBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, eventViewModel)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mTiltle?.manageFollowedEvents?.get(position))
    }

    override fun getItemCount() = mTiltle?.manageFollowedEvents?.size ?: 0

    fun setData(launch: ManageFollowedEventsQuery.Data?) {
        mTiltle = launch
    }


}