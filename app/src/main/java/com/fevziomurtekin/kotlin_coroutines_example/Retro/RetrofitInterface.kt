package com.fevziomurtekin.kotlin_coroutines_example.Retro

import com.fevziomurtekin.kotlin_coroutines_example.Model.Repos.ReposResponse
import com.fevziomurtekin.kotlin_coroutines_example.Model.User.UserResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

const val BASE_URL : String ="https://api.github.com/users/"
const val USERNAME : String ="GITHUB USERNAME"

interface RetrofitInterface {
    @GET(USERNAME+"/repos")
    fun getRepos(
        @Header("Authorization") credentials: String
    ):Deferred<List<ReposResponse>>

    @GET(USERNAME)
    fun getUserInfo(@Header("Authorization") credentials: String
    ):Deferred<UserResponse>


    /*created retrofit object.*/
    companion object {
        fun create(): RetrofitInterface{

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitInterface::class.java)
        }
    }
}