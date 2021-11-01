package com.thenewj.newj.ui.main.explore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.exception.ApolloException
import com.thenewj.newj.databinding.FragmentExploreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    private val exploreViewModel: ExploreViewModel by viewModels()
    private lateinit var binding: FragmentExploreBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDataGraphql()
        initListener()
    }

    private fun initListener() {
        binding.btnRetry.setOnClickListener {
            initDataGraphql()
        }
    }

    private fun initDataGraphql() {
        binding.adapter = exploreViewModel.mExploreSectionsAdapter
        lifecycleScope.launchWhenResumed {
            binding.shimmerLayout.visibility = View.VISIBLE
            binding.shimmerLayout.startShimmer()
            binding.llError.visibility = View.GONE
            val response = try {
                exploreViewModel.getExploreViewSections()
            } catch (e: ApolloException) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                binding.llError.visibility = View.VISIBLE
                return@launchWhenResumed
            }
            val launch = response.data
            if (launch == null || response.hasErrors()) {
                binding.shimmerLayout.stopShimmer()
                binding.shimmerLayout.visibility = View.GONE
                binding.llError.visibility = View.VISIBLE
                Log.d("Error", "${response.errors?.get(0)?.message}")
                return@launchWhenResumed
            }
            binding.shimmerLayout.stopShimmer()
            binding.shimmerLayout.visibility = View.GONE
            binding.llError.visibility = View.GONE
            binding.rvExplore.visibility = View.VISIBLE
            exploreViewModel.initDataToExploreAdapter(launch)
        }
    }
}