package com.thenewj.newj.ui.main.categoryprofile

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.google.android.material.snackbar.Snackbar
import com.thenewj.newj.CategoryFullFeedViewQuery
import com.thenewj.newj.ExploreViewQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryProfileActivityViewModel @Inject constructor(
    private val apolloClient: ApolloClient
) : ViewModel() {
    var mCategoryProfileStoriesAdapter: CategoryProfileStoriesAdapter = CategoryProfileStoriesAdapter( this)

    suspend fun getCategoryFullFeedData(categoryId:String?): Response<CategoryFullFeedViewQuery.Data> {
        return apolloClient.query(CategoryFullFeedViewQuery(categoryId?:"")).await()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initDataToCategoryProfileStoriesAdapter(launch: CategoryFullFeedViewQuery.Data?) {
        mCategoryProfileStoriesAdapter.setData(launch)
        mCategoryProfileStoriesAdapter.notifyDataSetChanged()
    }

    fun onClickNewsBriefCard(v: View, brief: CategoryFullFeedViewQuery.NewsBrief) {
        Snackbar.make(
            v,
            "Work in progress...",
            Snackbar.LENGTH_SHORT
        ).show()
    }
    fun onClickFollow(v:View,feed:CategoryFullFeedViewQuery.CategoryFullFeedView){
        Snackbar.make(
            v,
            "Work in progress...",
            Snackbar.LENGTH_SHORT
        ).show()
    }
}