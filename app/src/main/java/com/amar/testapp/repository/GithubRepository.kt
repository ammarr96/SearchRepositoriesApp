package com.amar.testapp.repository

import com.amar.testapp.model.RepositoriesResponseObject
import com.amar.testapp.network.ApiService
import com.amar.testapp.network.Repository
import com.amar.testapp.network.RetrofitFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubRepository {

    var apiService: ApiService = RetrofitFactory().makeApiService()!!

    fun getRepositorisByQuery(query: String, callback: GetDataCallback) {

        val call =  apiService.searchRepositories(query)

        call.enqueue(object: Callback<RepositoriesResponseObject?> {
            override fun onResponse(call: Call<RepositoriesResponseObject?>, response: Response<RepositoriesResponseObject?>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body()?.items ?: ArrayList())
                }
                else {
                    callback.onError("Error getting data")
                }
            }

            override fun onFailure(call: Call<RepositoriesResponseObject?>, t: Throwable) {
                callback.onError("Check your internet connection")
                t.printStackTrace()
            }

        })

    }
}