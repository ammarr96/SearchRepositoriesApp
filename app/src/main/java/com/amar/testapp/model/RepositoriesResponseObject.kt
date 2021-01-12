package com.amar.testapp.model

import com.amar.testapp.network.Repository
import com.google.gson.annotations.SerializedName

data class RepositoriesResponseObject(

    @SerializedName("total_count")
    var totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    var items: List<Repository>

)