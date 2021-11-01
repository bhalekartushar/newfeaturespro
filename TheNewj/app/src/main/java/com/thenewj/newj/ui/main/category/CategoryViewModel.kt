package com.thenewj.newj.ui.main.category

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.thenewj.newj.ManageFollowedCategoriesQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val apolloClient: ApolloClient
    ) : ViewModel() {
        var mCategoryListAdapter: CategoryListAdapter = CategoryListAdapter(this)

        suspend fun getExploreViewSections(): Response<ManageFollowedCategoriesQuery.Data> {
            return apolloClient.query(ManageFollowedCategoriesQuery()).await()
        }

        @SuppressLint("NotifyDataSetChanged")
        fun initDataToExploreAdapter(launch: ManageFollowedCategoriesQuery.Data?) {
            mCategoryListAdapter.setData(launch)
            mCategoryListAdapter.notifyDataSetChanged()
        }
}