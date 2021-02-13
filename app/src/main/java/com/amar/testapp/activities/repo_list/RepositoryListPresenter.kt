package com.amar.testapp.activities.repo_list

import androidx.annotation.RestrictTo
import com.amar.testapp.network.Repository
import com.amar.testapp.repository.GetDataCallback
import com.amar.testapp.repository.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RepoListPresenter(view: RepoListView, repository: GithubRepository) {

    var gitRepository: GithubRepository
    var repoListView: RepoListView


    init {
        gitRepository = repository
        repoListView = view
    }

    fun getRepositoriesBySearchString(query: String)  {

        repoListView.showProgressBar()

        gitRepository.getRepositorisByQuery(query, object: GetDataCallback {
            override fun onSuccess(list: ArrayList<Repository>) {
                repoListView.showRepoList(list)
                repoListView.hideProgressBar()
            }

            override fun onError(error: String) {
                repoListView.hideProgressBar()
                repoListView.showErrorMessage(error)
            }

        })

    }

}