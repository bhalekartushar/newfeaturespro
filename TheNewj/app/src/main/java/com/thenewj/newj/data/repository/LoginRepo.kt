package com.thenewj.newj.data.repository

import com.thenewj.newj.data.local.dao.AuthDao
import javax.inject.Inject

class LoginRepo @Inject constructor(private val credentialsDao: AuthDao) {


}