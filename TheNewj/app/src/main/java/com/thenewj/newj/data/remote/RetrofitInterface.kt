package com.thenewj.newj.data.remote

import com.thenewj.newj.data.local.entity.Auth
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitInterface {

    //POST METHODS
    @POST("accounts/authmobile")
    fun postGetAuthMobile(@Body authMobileRequest: Auth?): Observable<Auth?>?

}