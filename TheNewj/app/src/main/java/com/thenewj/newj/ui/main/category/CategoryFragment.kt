package com.thenewj.newj.ui.main.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.exception.ApolloException
import com.thenewj.newj.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
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
        binding.adapter = categoryViewModel.mCategoryListAdapter
        lifecycleScope.launchWhenResumed {

            binding.llError.visibility = View.GONE
            val response = try {
                categoryViewModel.getExploreViewSections()
            } catch (e: ApolloException) {

                binding.llError.visibility = View.VISIBLE
                return@launchWhenResumed
            }
            val launch = response.data
            if (launch == null || response.hasErrors()) {

                binding.llError.visibility = View.VISIBLE
                Log.d("Error", "${response.errors?.get(0)?.message}")
                return@launchWhenResumed
            }

            binding.llError.visibility = View.GONE
            binding.showCategorylist.visibility = View.VISIBLE
            categoryViewModel.initDataToExploreAdapter(launch)
        }
    }
}