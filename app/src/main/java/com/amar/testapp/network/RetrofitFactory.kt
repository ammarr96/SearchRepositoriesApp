package com.amar.testapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory {

    val BASE_URL: String =  "https://api.github.com/";

    fun makeApiService(): ApiService? {
        val httpClient = OkHttpClient.Builder()
        httpClient.readTimeout(240, TimeUnit.SECONDS)
        httpClient.connectTimeout(240, TimeUnit.SECONDS)
        httpClient.writeTimeout(240, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
            .create(ApiService::class.java)
    }

}