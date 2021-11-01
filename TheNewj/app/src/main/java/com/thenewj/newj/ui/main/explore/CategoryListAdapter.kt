package com.thenewj.newj.ui.main.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.ExploreViewQuery
import com.thenewj.newj.databinding.ListItemCategoryBinding

class CategoryListAdapter(private val mExploreViewModel: ExploreViewModel) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private var mCategories: List<ExploreViewQuery.MoreCategory?>? = null

    class ViewHolder(val binding: ListItemCategoryBinding, val exploreViewModel: ExploreViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: ExploreViewQuery.MoreCategory?) {
            binding.apply {
                this.category = category
                this.vm = exploreViewModel
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemCategoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, mExploreViewModel)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mCategories?.get(position))
    }

    override fun getItemCount() = mCategories?.size ?: 0
    fun setData(categories: List<ExploreViewQuery.MoreCategory?>?) {
        mCategories = categories
    }
}