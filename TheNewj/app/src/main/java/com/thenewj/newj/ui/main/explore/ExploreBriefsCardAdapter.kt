package com.thenewj.newj.ui.main.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thenewj.newj.ExploreViewQuery
import com.thenewj.newj.databinding.ListItemNewsCardBinding

class ExploreBriefsCardAdapter(private val exploreViewModel: ExploreViewModel) :
    RecyclerView.Adapter<ExploreBriefsCardAdapter.ViewHolder>() {

    private var mShowBriefCategory: Boolean? = null
    private var mBriefs: List<ExploreViewQuery.Brief>? = null

    class ViewHolder(val binding: ListItemNewsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(brief: ExploreViewQuery.Brief?, mShowBriefCategory: Boolean?, exploreViewModel: ExploreViewModel) {
            binding.apply {
                this.brief = brief
                this.showBriefCategory = mShowBriefCategory
                this.vm = exploreViewModel
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemNewsCardBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(mBriefs?.get(position), mShowBriefCategory, exploreViewModel)
    }

    override fun getItemCount() = mBriefs?.size ?: 0
    fun setData(briefs: List<ExploreViewQuery.Brief>?, showBriefCategory: Boolean?) {
        mBriefs = briefs
        mShowBriefCategory = showBriefCategory
    }
}