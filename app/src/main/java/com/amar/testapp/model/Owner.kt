package com.amar.testapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Owner (

        @SerializedName("login")
        var username: String,

        @SerializedName("avatar_url")
        var avatarUrl: String,

        @SerializedName("html_url")
        var htmlUrl: String

) : Serializable