package com.amar.testapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amar.testapp.R
import com.amar.testapp.adapters.CustomAdapter
import com.amar.testapp.model.RepositoriesResponseObject
import com.amar.testapp.network.RetrofitFactory
import com.amar.testapp.network.ApiService
import com.amar.testapp.network.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    lateinit var apiService: ApiService
    lateinit var repositoryList: List<Repository>
    lateinit var adapter: CustomAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView: EditText = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);

        apiService = RetrofitFactory().makeApiService()!!
        repositoryList = mutableListOf()

        searchView.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchRepositories(searchView.text.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        recyclerView.layoutManager = LinearLayoutManager(applicationContext);
        adapter = CustomAdapter(repositoryList)
        recyclerView.adapter = adapter;

    }

    fun searchRepositories(query: String) {

        val call: Call<RepositoriesResponseObject?> = apiService.searchRepositories(query)

        call.enqueue(object : Callback<RepositoriesResponseObject?> {
            override fun onResponse(call: Call<RepositoriesResponseObject?>, response: Response<RepositoriesResponseObject?>) {
                if (response.isSuccessful()) {
                    repositoryList = response.body()?.items ?: mutableListOf()

                    repositoryList = repositoryList.sorted()
                    val newAdapter = CustomAdapter(repositoryList)
                    recyclerView.swapAdapter(newAdapter, false)
                }
            }

            override fun onFailure(call: Call<RepositoriesResponseObject?>, t: Throwable) {
            }
        })
    }
}

