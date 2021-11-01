package com.thenewj.newj.ui.main.event

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.thenewj.newj.ManageFollowedEventsQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(
    private val apolloClient: ApolloClient
) : ViewModel() {
    var mEventListAdapter: EventListAdapter = EventListAdapter(this)

    suspend fun getExploreViewSections(): Response<ManageFollowedEventsQuery.Data> {
        return apolloClient.query(ManageFollowedEventsQuery()).await()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initDataToExploreAdapter(launch: ManageFollowedEventsQuery.Data?) {
        mEventListAdapter.setData(launch)
        mEventListAdapter.notifyDataSetChanged()
    }

}