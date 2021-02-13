package com.amar.testapp.network

import com.amar.testapp.model.Owner
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat

data class Repository (

        @SerializedName("name")
        var name: String,

        @SerializedName("updated_at")
        var updatedAt: String,

        @SerializedName("created_at")
        var createdAt: String,

        @SerializedName("description")
        var description: String,

        @SerializedName("owner")
        var owner: Owner,

        @SerializedName("forks")
        var forkCount: Int,

        @SerializedName("open_issues")
        var issuesCount: Int,

        @SerializedName("watchers")
        var watchersCont: Int,

        @SerializedName("language")
        var language: String,

        @SerializedName("html_url")
        var htmlUrl: String,

        @SerializedName("stargazers_count")
        var stargazersCount: Int

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
