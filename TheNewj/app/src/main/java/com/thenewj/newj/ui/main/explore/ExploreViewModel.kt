package com.thenewj.newj.ui.main.explore

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.google.gson.Gson
import com.thenewj.newj.ExploreViewQuery
import com.thenewj.newj.ui.main.categoryprofile.CategoryProfileActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val apolloClient: ApolloClient
) : ViewModel() {
    var mExploreSectionsAdapter: ExploreSectionsAdapter = ExploreSectionsAdapter(this)

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    suspend fun getExploreViewSections(): Response<ExploreViewQuery.Data> {
        return apolloClient.query(ExploreViewQuery()).await()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initDataToExploreAdapter(launch: ExploreViewQuery.Data) {
        mExploreSectionsAdapter.setData(launch)
        mExploreSectionsAdapter.notifyDataSetChanged()
    }

    fun onClickNewsCard(v: View, brief: ExploreViewQuery.Brief) {
        v.context.startActivity(Intent(v.context, CategoryProfileActivity::class.java))
    }

    fun onClickCategory(v: View, category: ExploreViewQuery.MoreCategory?) {
        v.context.startActivity(Intent(v.context, CategoryProfileActivity::class.java).apply {
            putExtra("Category", category?.id)
        })
    }
}