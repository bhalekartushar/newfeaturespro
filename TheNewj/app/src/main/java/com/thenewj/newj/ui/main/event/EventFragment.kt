package com.thenewj.newj.ui.main.event

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.exception.ApolloException
import com.thenewj.newj.databinding.FragmentEventBinding

class EventFragment : Fragment() {

    private val eventViewModel: EventViewModel by viewModels()
    private lateinit var binding: FragmentEventBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventBinding.inflate(inflater, container, false)
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
        binding.adapter = eventViewModel.mEventListAdapter
        lifecycleScope.launchWhenResumed {

            binding.llError.visibility = View.GONE
            val response = try {
                eventViewModel.getExploreViewSections()
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
            binding.showEventlist.visibility = View.VISIBLE
            eventViewModel.initDataToExploreAdapter(launch)
        }
    }
}