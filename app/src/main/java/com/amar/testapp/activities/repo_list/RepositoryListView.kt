package com.amar.testapp.activities.repo_list

import com.amar.testapp.network.Repository

interface RepoListView {

    fun showRepoList(list: ArrayList<Repository>)

    fun showProgressBar()

    fun hideProgressBar()

    fun showErrorMessage(message: String)

}