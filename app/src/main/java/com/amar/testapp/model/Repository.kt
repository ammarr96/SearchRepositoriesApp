package com.amar.testapp.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat

data class Repository (

    @SerializedName("name")
    var name: String,

    @SerializedName("updated_at")
    var updatedAt: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("owner")
    var owner: Owner

    ) : Serializable, Comparable<Repository> {

    override fun compareTo(other: Repository): Int {
        val format = "yyyy-MM-dd'T'HH:mm:ssX"
        val date1 = SimpleDateFormat(format).parse(updatedAt)
        val date2 = SimpleDateFormat(format).parse(other.updatedAt)

        if (date1.after(date2)) {
            return -1
        }
        else {
            return 1
        }

    }
}

data class Owner (

    @SerializedName("login")
    var username: String

    ) : Serializable
