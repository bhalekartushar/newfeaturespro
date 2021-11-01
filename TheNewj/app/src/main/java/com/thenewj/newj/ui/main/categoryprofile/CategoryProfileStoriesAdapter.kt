package com.thenewj.newj.ui.main.categoryprofile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.CategoryFullFeedViewQuery
import com.thenewj.newj.databinding.ListItemStoriesBinding

class CategoryProfileStoriesAdapter(private val exploreViewModel: CategoryProfileActivityViewModel) :
    RecyclerView.Adapter<CategoryProfileStoriesAdapter.ViewHolder>() {
    private var mStories: CategoryFullFeedViewQuery.Data? = null

    class ViewHolder(val binding: ListItemStoriesBinding, viewModel: CategoryProfileActivityViewModel) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(section: CategoryFullFeedViewQuery.NewsBrief?) {
            binding.story = section
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemStoriesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, exploreViewModel)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mStories?.categoryFullFeedView?.feed?.newsBriefs?.get(position))
    }

    override fun getItemCount() = mStories?.categoryFullFeedView?.feed?.newsBriefs?.size ?: 0

    fun setData(launch: CategoryFullFeedViewQuery.Data?) {
        mStories = launch
    }
}