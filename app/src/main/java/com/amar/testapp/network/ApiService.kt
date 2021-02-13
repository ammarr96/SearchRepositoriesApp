package com.amar.testapp.network

import com.amar.testapp.model.RepositoriesResponseObject
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService  {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Call<RepositoriesResponseObject>

}