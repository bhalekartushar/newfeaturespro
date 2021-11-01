package com.thenewj.newj.ui.main.categoryprofile

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.thenewj.newj.R
import com.thenewj.newj.databinding.ActivityCategoryProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryProfileActivity : AppCompatActivity() {

    private var categoryId: String? = null
    private lateinit var binding: ActivityCategoryProfileBinding
    private val categoryProfileActivityViewModel: CategoryProfileActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        categoryId = intent.getStringExtra("Category")
        initDataGraphql()
        initListener()
    }

    private fun initListener() {
        binding.btnRetry.setOnClickListener {
            initDataGraphql()
        }
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initDataGraphql() {
        binding.adapter = categoryProfileActivityViewModel.mCategoryProfileStoriesAdapter
        lifecycleScope.launchWhenResumed {
            binding.shimmerLayout.visibility = android.view.View.VISIBLE
            binding.shimmerLayout.startShimmer()
            binding.llError.visibility = android.view.View.GONE
            val response = try {
                categoryProfileActivityViewModel.getCategoryFullFeedData(categoryId)
            } catch (e: com.apollographql.apollo.exception.ApolloException) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = android.view.View.GONE
                binding.llError.visibility = android.view.View.VISIBLE
                return@launchWhenResumed
            }
            val launch = response.data
            if (launch == null || response.hasErrors()) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = android.view.View.GONE
                binding.llError.visibility = android.view.View.VISIBLE
                Log.d("Error", "${response.errors?.get(0)?.message}")
                return@launchWhenResumed
            }
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = android.view.View.GONE
            binding.llError.visibility = android.view.View.GONE
            binding.rvStories.visibility = android.view.View.VISIBLE
            binding.categoryView = launch.categoryFullFeedView
            if (launch.categoryFullFeedView?.isFollowing == true) {
                binding.tvFollowed.background =
                    ContextCompat.getDrawable(this@CategoryProfileActivity, R.drawable.shape_rectangle_follow_selected)
                binding.tvFollowed.setTextColor(resources.getColor(R.color.black))
            } else {
                binding.tvFollowed.background =
                    ContextCompat.getDrawable(this@CategoryProfileActivity, R.drawable.shape_rectangle_follow_default)
                binding.tvFollowed.setTextColor(resources.getColor(R.color.white))
            }
            categoryProfileActivityViewModel.initDataToCategoryProfileStoriesAdapter(launch)
        }
    }

}