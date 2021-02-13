package com.amar.testapp.activities.repo_list

import com.amar.testapp.model.SortOption
import com.amar.testapp.network.Repository
import com.amar.testapp.repository.GetDataCallback
import com.amar.testapp.repository.GithubRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList

class RepositoryListPresenter(view: RepositoryListView, repository: GithubRepository) {

    var gitRepository: GithubRepository
    var repositoryListView: RepositoryListView
    var repositoryList: ArrayList<Repository>
    var sortOption: SortOption = SortOption.DEFAULT

    init {
        gitRepository = repository
        repositoryListView = view
        repositoryList = ArrayList()
    }

    fun getRepositoriesBySearchString(query: String)  {

        repositoryListView.showProgressBar()

        gitRepository.getRepositorisByQuery(query, object : GetDataCallback {
            override fun onSuccess(list: ArrayList<Repository>) {
                repositoryList = list
                sortRepositoryList()
                //repositoryListView.showRepoList(list)
                repositoryListView.hideProgressBar()
            }

            override fun onError(error: String) {
                repositoryListView.hideProgressBar()
                repositoryListView.showErrorMessage(error)
            }

        })

    }

    fun changeSortOption(sortOption: SortOption) {
        if (this.sortOption != sortOption) {
            this.sortOption = sortOption
            sortRepositoryList()
        }
    }

    private fun sortRepositoryList() {
        if (sortOption == SortOption.DEFAULT) {

        }
        else if (sortOption == SortOption.BY_STARS) {
            Collections.sort(repositoryList,
                Comparator<Repository> { o1, o2 -> o2?.stargazersCount!!.compareTo(o1.stargazersCount) })

        }
        else if (sortOption == SortOption.BY_FORKS) {
            Collections.sort(repositoryList,
                Comparator<Repository> { o1, o2 -> o2?.forkCount!!.compareTo(o1.forkCount) })
        }
        else if (sortOption == SortOption.BY_UPDATE_TIME) {

            val myCustomComparator =  Comparator<Repository> { a, b ->

                val format = "yyyy-MM-dd'T'HH:mm:ssX"
                val date1 = SimpleDateFormat(format).parse(a.updatedAt)
                val date2 = SimpleDateFormat(format).parse(b.updatedAt)

                when {
                    (date1.after(date2)) -> -1
                    else -> 1
                }
            }
            repositoryList.sortWith(myCustomComparator)

        }
        repositoryListView.showRepoList(repositoryList)
    }

}