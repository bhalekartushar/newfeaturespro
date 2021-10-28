package com.thenewj.newj.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.thenewj.newj.utils.Constants

@Entity(tableName = Constants.TABLE_AUTH)
class Auth{
    @PrimaryKey
    var id = 1

    @SerializedName("UserName")
    @Expose
    var userName: String? = null

    @SerializedName("Password")
    @Expose
    var password: String? = null

    @SerializedName("LanguageCode")
    @Expose
    var languageCode: String? = null
}