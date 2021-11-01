package com.thenewj.newj.ui.main.interest

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.thenewj.newj.ManageFollowedInterestsQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InterestViewModel  @Inject constructor(
    private val apolloClient: ApolloClient
) : ViewModel() {
    var mInterestListAdapter: InterestListAdapter = InterestListAdapter(this)

    suspend fun getExploreViewSections(): Response<ManageFollowedInterestsQuery.Data> {
        return apolloClient.query(ManageFollowedInterestsQuery()).await()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initDataToExploreAdapter(launch: ManageFollowedInterestsQuery.Data?) {
        mInterestListAdapter.setData(launch)
        mInterestListAdapter.notifyDataSetChanged()
    }
}