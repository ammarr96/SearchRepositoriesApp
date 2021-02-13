package com.amar.testapp.activities.repo_details

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.amar.testapp.R
import com.amar.testapp.network.Repository
import com.amar.testapp.util.Util.Companion.getDateAsString
import com.amar.testapp.util.Util.Companion.openLinkInBrowser
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class RepositoryDetailsActivity : AppCompatActivity() {

    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_details)

        repo = (intent.getSerializableExtra("repository") as? Repository)!!

        showData()

        val backIcon: AppCompatImageView = findViewById(R.id.backIcon)
        backIcon.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }

        })

    }

    private fun showData() {
        val repositoryNameTV: TextView = findViewById(R.id.repositoryNameTV);
        val creationDateTV: TextView = findViewById(R.id.creationDateTV);
        val lastUpdateDateTV: TextView = findViewById(R.id.lastUpdateDateTV);
        val ownerNameTV: TextView = findViewById(R.id.ownerNameTV);
        val descTV: TextView = findViewById(R.id.descTV);
        val languageTV: TextView = findViewById(R.id.languageTV);

        val imageView: CircleImageView = findViewById(R.id.image);
        Glide.with(applicationContext).load(repo.owner.avatarUrl).into(imageView)

        repositoryNameTV.text = repo.name
        repositoryNameTV.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        ownerNameTV.text = repo.owner.username
        ownerNameTV.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        descTV.text = repo.description
        languageTV.text = repo.language

        creationDateTV.text = getDateAsString(repo.createdAt)
        lastUpdateDateTV.text = getDateAsString(repo.updatedAt)

        ownerNameTV.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                openLinkInBrowser(applicationContext, repo.owner.htmlUrl)
            }
        })

        repositoryNameTV.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                openLinkInBrowser(applicationContext, repo.htmlUrl)
            }
        })
    }

}
