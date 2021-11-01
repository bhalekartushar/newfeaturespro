package com.thenewj.newj.ui.main.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.ManageFollowedCategoriesQuery
import com.thenewj.newj.databinding.ListItemManagedcategoryBinding

class CategoryListAdapter(private val categoryViewModel: CategoryViewModel) :
    RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    private var mTiltle: ManageFollowedCategoriesQuery.Data? = null

    class ViewHolder(val binding: ListItemManagedcategoryBinding, categoryViewModel: CategoryViewModel) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(title: ManageFollowedCategoriesQuery.ManageFollowedCategory?) {
            binding.title= title
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemManagedcategoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding, categoryViewModel)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mTiltle?.manageFollowedCategories?.get(position))
    }

    override fun getItemCount() = mTiltle?.manageFollowedCategories?.size ?: 0

    fun setData(launch: ManageFollowedCategoriesQuery.Data?) {
        mTiltle = launch
    }
}