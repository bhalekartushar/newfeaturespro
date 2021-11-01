package com.thenewj.newj.di

import com.apollographql.apollo.ApolloClient
import com.thenewj.newj.BuildConfig
import com.thenewj.newj.data.remote.RetrofitInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/*@Qualifier
annotation class WithHTTPS

@Qualifier
annotation class WithoutHTTPS*/

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun retrofitWithoutHttps(): RetrofitInterface {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .build()
            )
            .build()
        return retrofit.create(RetrofitInterface::class.java)
    }

    @Provides
    @Singleton
    fun getApolloClient(): ApolloClient {
        return ApolloClient.builder()
            .okHttpClient(
                OkHttpClient.Builder()
                    .build()
            )
            .serverUrl(BuildConfig.END_POINT)
            .build()
    }

    /*fun apolloClient(context: Context): ApolloClient {
        if (instance != null) {
            return instance!!
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(context))
            .build()

        instance = ApolloClient.builder()
            .serverUrl("https://apollo-fullstack-tutorial.herokuapp.com/graphql")
            .subscriptionTransportFactory(WebSocketSubscriptionTransport.Factory("wss://apollo-fullstack-tutorial.herokuapp.com/graphql", okHttpClient))
            .okHttpClient(okHttpClient)
            .build()

        return instance!!
    }

    private class AuthorizationInterceptor(val context: Context) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", User.getToken(context) ?: "")
                .build()

            return chain.proceed(request)
        }
    }*/
}