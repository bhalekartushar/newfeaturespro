package com.thenewj.newj.ui.main.interest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.ManageFollowedInterestsQuery
import com.thenewj.newj.databinding.ListItemInterestBinding
import com.thenewj.newj.ui.main.category.CategoryListAdapter

class InterestListAdapter(private val interestViewModel: InterestViewModel) :
    RecyclerView.Adapter<InterestListAdapter.ViewHolder>() {

    private var mTiltle: ManageFollowedInterestsQuery.Data? = null

    class ViewHolder(val binding: ListItemInterestBinding, interestViewModel: InterestViewModel) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(title: ManageFollowedInterestsQuery.ManageFollowedInterest?) {
            binding.title= title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemInterestBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, interestViewModel)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mTiltle?.manageFollowedInterests?.get(position))
    }

    override fun getItemCount() = mTiltle?.manageFollowedInterests?.size ?: 0

    fun setData(launch: ManageFollowedInterestsQuery.Data?) {
        mTiltle = launch
    }
}