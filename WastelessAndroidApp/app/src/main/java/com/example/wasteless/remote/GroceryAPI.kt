package com.example.wasteless.remote

import com.example.wasteless.remote.model.GroceryList
import com.example.wasteless.remote.model.User
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface GroceryAPI {

    @GET("/wasteless/lists/get/{userId}")
    suspend fun getUserLists(@Path("userId") userId: Int): List<GroceryList>

    @POST("wasteless/users/login")
    suspend fun attemptLogin(@Body user: User): Int

    @POST("wasteless/lists/add/{userId}")
    suspend fun addList(@Path("userId") userId: Int, @Body groceryList: GroceryList)

    // The Retrofit class generates an implementation for the interface
    companion object {
        fun create(): GroceryAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8080")
                .client(client)
                .build()
            return retrofit.create(GroceryAPI::class.java)
        }
    }
}
