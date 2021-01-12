package com.amar.testapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.amar.testapp.R
import com.amar.testapp.network.Repository
import java.text.SimpleDateFormat

class RepoDetailsActivity : AppCompatActivity() {

    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        repo = (intent.getSerializableExtra("repo") as? Repository)!!

        showData()

    }

    fun showData() {
        val nameTV: TextView = findViewById(R.id.nameTV);
        val dateTV: TextView = findViewById(R.id.dateTV);
        val ownerTV: TextView = findViewById(R.id.ownerTV);
        val descTV: TextView = findViewById(R.id.descTV);

        nameTV.text = repo.name
        ownerTV.text = repo.owner.username
        descTV.text = repo.description

        val format = "yyyy-MM-dd'T'HH:mm:ssX"
        val date1 = SimpleDateFormat(format).parse(repo.updatedAt)
        val format2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        dateTV.text = format2.format(date1)
    }
}
