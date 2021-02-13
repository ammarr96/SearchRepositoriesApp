package com.amar.testapp.activities.repo_details

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.amar.testapp.R
import com.amar.testapp.network.Repository
import com.amar.testapp.util.Util.Companion.getDateAsString
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class RepoDetailsActivity : AppCompatActivity() {

    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        repo = (intent.getSerializableExtra("repo") as? Repository)!!

        showData()

    }

    private fun showData() {
        val repoNameTV: TextView = findViewById(R.id.repoNameTV);
        val creationDateTV: TextView = findViewById(R.id.creationDateTV);
        val lastUpdateDateTV: TextView = findViewById(R.id.lastUpdateDateTV);
        val ownerNameTV: TextView = findViewById(R.id.ownerNameTV);
        val descTV: TextView = findViewById(R.id.descTV);

        val imageView: CircleImageView = findViewById(R.id.image);
        Glide.with(applicationContext).load(repo.owner.avatarUrl).into(imageView)

        repoNameTV.text = repo.name
        repoNameTV.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        ownerNameTV.text = repo.owner.username
        ownerNameTV.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        descTV.text = repo.description

        creationDateTV.text = getDateAsString(repo.createdAt)
        lastUpdateDateTV.text = getDateAsString(repo.updatedAt)

        ownerNameTV.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                openLinkInBrowser(repo.owner.html_url)
            }
        })

        repoNameTV.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                openLinkInBrowser(repo.html_url)
            }
        })
    }

    fun openLinkInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}
