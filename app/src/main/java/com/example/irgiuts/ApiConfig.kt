package com.example.irgiuts

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

interface ApiService {

    @GET("search/users")
    @Headers("Authorization: Bearer $TOKEN")
    fun getUsers(
        @Query("q") username: String = "irginst"
    ): Call<GithubRes>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UsersItem>

    companion object {
        const val TOKEN = "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"
    }
}