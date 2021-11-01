package com.thenewj.newj.ui.main.explore

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.ExploreViewQuery
import com.thenewj.newj.databinding.ListItemExploreBinding
import com.thenewj.newj.databinding.ListItemExploreCategoryBinding

class ExploreSectionsAdapter(private val exploreViewModel: ExploreViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mSections: ExploreViewQuery.Data? = null

    companion object {
        const val COMMON_SECTION = 1
        const val MORE_CATEGORIES_SECTION = 0
    }

    class ViewHolderCommonSection(val binding: ListItemExploreBinding, exploreViewModel: ExploreViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        private var mExploreBriefsCardsAdapter: ExploreBriefsCardAdapter = ExploreBriefsCardAdapter(exploreViewModel)

        init {
            binding.adapter = mExploreBriefsCardsAdapter
        }

        @SuppressLint("NotifyDataSetChanged")
        fun bind(section: ExploreViewQuery.Section?) {
            binding.section = section
            mExploreBriefsCardsAdapter.setData(section?.briefs, section?.sectionType?.showBriefCategory)
            mExploreBriefsCardsAdapter.notifyDataSetChanged()
        }
    }

    class ViewHolderCategorySection(val binding: ListItemExploreCategoryBinding, exploreViewModel: ExploreViewModel) :
        RecyclerView.ViewHolder(binding.root) {
        private var mExploreMoreCategoriesAdapter: CategoryListAdapter = CategoryListAdapter(exploreViewModel)

        init {
            binding.adapter = mExploreMoreCategoriesAdapter
        }

        @SuppressLint("NotifyDataSetChanged")
        fun bind(categories: List<ExploreViewQuery.MoreCategory?>?) {
            mExploreMoreCategoriesAdapter.setData(categories)
            mExploreMoreCategoriesAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == COMMON_SECTION) {
            val binding = ListItemExploreBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            ViewHolderCommonSection(binding, exploreViewModel)
        } else {
            val binding = ListItemExploreCategoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
            ViewHolderCategorySection(binding, exploreViewModel)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ViewHolderCommonSection)
            viewHolder.bind(mSections?.exploreView?.sections?.get(position))
        else if (viewHolder is ViewHolderCategorySection)
            viewHolder.bind(mSections?.exploreView?.moreCategories)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < mSections?.exploreView?.sections?.size ?: 0) COMMON_SECTION else MORE_CATEGORIES_SECTION
    }

    override fun getItemCount() = (mSections?.exploreView?.sections?.size ?: 0) + 1

    fun setData(launch: ExploreViewQuery.Data) {
        mSections = launch
    }
}