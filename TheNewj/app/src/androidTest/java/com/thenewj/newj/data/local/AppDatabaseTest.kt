package com.thenewj.newj.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.thenewj.newj.data.local.dao.AuthDao
import com.thenewj.newj.data.local.entity.Auth
import com.thenewj.newj.getOrAwaitValue
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppDatabaseTest : TestCase() {
    private lateinit var db: AppDatabase
    private lateinit var dao: AuthDao

    /*@get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()*/ //add this in case of test failure of fetching data in runblocking

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.authDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun writeAndReadDatabase() {
        var credential: Auth? = Auth()
        credential = dao.getRow(0)
        val lst = dao.getListData().getOrAwaitValue()
        assertThat(!credential?.userName.isNullOrBlank()).isTrue()
        assertThat(lst.isNotEmpty()).isTrue()
    }

}