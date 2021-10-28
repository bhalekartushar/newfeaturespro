package com.thenewj.newj.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.thenewj.newj.data.local.entity.Auth
import com.thenewj.newj.utils.Constants

@Dao
interface AuthDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(product: List<Auth>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Auth): Long

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(product: Auth): Int

    fun insertOrUpdate(cred: Auth): Int {
        return if (cred.id == null || cred.id == 0) {
            insert(cred).toInt()
        } else {
            if (getRow(cred.id ?: 0) != null) {
                update(cred)
            } else {
                insert(cred).toInt()
            }
        }
    }

    @Query("SELECT * FROM ${Constants.TABLE_AUTH} WHERE id =:id limit 1")
    fun getRow(id: Int): Auth?

    @Query("SELECT * FROM ${Constants.TABLE_AUTH}")
    fun getListData(): LiveData<List<Auth>>

    @Query("DELETE FROM ${Constants.TABLE_AUTH} WHERE id=:id ")
    fun deleteRow(id: Int, mobileNo: String)
}