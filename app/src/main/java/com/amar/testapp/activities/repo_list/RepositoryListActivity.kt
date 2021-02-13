package com.amar.testapp.activities.repo_list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amar.testapp.R
import com.amar.testapp.adapters.RepositoryListAdapter
import com.amar.testapp.model.SortOption
import com.amar.testapp.network.Repository
import com.amar.testapp.repository.GithubRepository


class RepositoryListActivity : AppCompatActivity(), RepositoryListView{

    lateinit var adapter: RepositoryListAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var presenter: RepositoryListPresenter
    lateinit var progressBar: ProgressBar
    lateinit var sortImage: AppCompatImageView
    lateinit var searchView: EditText
    var sortOption: SortOption = SortOption.DEFAULT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_list)

        presenter = RepositoryListPresenter(this, GithubRepository())

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        searchView = findViewById(R.id.searchView);
        sortImage = findViewById(R.id.sortImage);

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(applicationContext);
        adapter = RepositoryListAdapter(ArrayList(), this)
        recyclerView.adapter = adapter;

        val popupMenu = PopupMenu(applicationContext, sortImage)
        popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            val menu: Menu = popupMenu.getMenu()
            for (i in 0 until menu.size()) {
                menu.getItem(i).setChecked(false)
            }
            item.isChecked = true
            when (item.itemId) {
                R.id.menu_stars -> {
                    sortOption = SortOption.BY_STARS
                }
                R.id.menu_forks -> {
                    sortOption = SortOption.BY_FORKS
                }
                R.id.menu_date -> {
                    sortOption = SortOption.BY_UPDATE_TIME
                }

            }
            //presenter.getRepositoriesBySearchString(searchView.text.toString(), sortOption)
            presenter.changeSortOption(sortOption)
            true
        })

        sortImage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                popupMenu.show()
            }
        })

        searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (searchView.text.isNotEmpty()) {
                    presenter.getRepositoriesBySearchString(searchView.text.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

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

