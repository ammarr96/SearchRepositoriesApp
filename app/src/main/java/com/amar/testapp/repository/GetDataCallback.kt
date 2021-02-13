package com.amar.testapp.repository

import com.amar.testapp.network.Repository

interface GetDataCallback {

    fun onSuccess(list: ArrayList<Repository>)
    fun onError(error: String)

}