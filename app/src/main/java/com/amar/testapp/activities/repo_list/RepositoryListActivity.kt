package com.amar.testapp.activities.repo_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amar.testapp.R
import com.amar.testapp.adapters.RepoListAdapter
import com.amar.testapp.network.Repository
import com.amar.testapp.repository.GithubRepository


class RepoListActivity : AppCompatActivity(), RepoListView{

    lateinit var adapter: RepoListAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var presenter: RepoListPresenter
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        presenter = RepoListPresenter(this, GithubRepository())

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);


        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))

        val searchView: EditText = findViewById(R.id.searchView);

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //searchRepositories(searchView.text.toString() ?: "")
                if (searchView.text.isNotEmpty()) {
                    presenter.getRepositoriesBySearchString(searchView.text.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        recyclerView.layoutManager = LinearLayoutManager(applicationContext);
        adapter = RepoListAdapter(ArrayList(), this)

        recyclerView.adapter = adapter;

    }


    override fun showRepoList(list: ArrayList<Repository>) {
        adapter.setItems(list)
        adapter.notifyDataSetChanged()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}

