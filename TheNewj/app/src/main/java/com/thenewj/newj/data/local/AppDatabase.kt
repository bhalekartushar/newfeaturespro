package com.thenewj.newj.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thenewj.newj.data.local.dao.AuthDao
import com.thenewj.newj.data.local.entity.Auth

/**
 * Created by Kumar Chavan.
 */
@Database(
    entities = [Auth::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
}