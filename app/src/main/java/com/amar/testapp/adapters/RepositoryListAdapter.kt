package com.amar.testapp.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.amar.testapp.R
import com.amar.testapp.activities.repo_details.RepositoryDetailsActivity
import com.amar.testapp.network.Repository
import com.amar.testapp.util.Util
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView


class RepoListAdapter(private var dataSet: ArrayList<Repository>, private var context: Context) : RecyclerView.Adapter<RepoListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val repositoryNameTV: TextView
        val usernameTV: TextView
        val forksCountTV: TextView
        val watchersCountTV: TextView
        val issuesCountTV: TextView
        val userImageView: CircleImageView

        init {
            repositoryNameTV = view.findViewById(R.id.repositoryNameTV)
            usernameTV = view.findViewById(R.id.usernameTV)
            forksCountTV = view.findViewById(R.id.forksCountTV)
            watchersCountTV = view.findViewById(R.id.watchersCountTV)
            issuesCountTV = view.findViewById(R.id.issuesCountTV)
            userImageView = view.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.repositoy_listview_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val repository = dataSet.get(position)

        viewHolder.repositoryNameTV.text = repository.name
        viewHolder.usernameTV.text = repository.owner.username

        viewHolder.forksCountTV.text = String.format("%s: %d", context.getString(R.string.forks), repository.forkCount)
        viewHolder.watchersCountTV.text = String.format("%s: %d", context.getString(R.string.watchers), repository.watchersCont)
        viewHolder.issuesCountTV.text = String.format("%s: %d", context.getString(R.string.issues), repository.issuesCount)

        Glide.with(viewHolder.userImageView).load(repository.owner.avatarUrl).into(viewHolder.userImageView)

        viewHolder.userImageView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Util.openLinkInBrowser(context, repository.owner.html_url)
            }
        })

        viewHolder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val intent = Intent(context, RepositoryDetailsActivity::class.java)
                intent.putExtra("repository", dataSet.get(position))
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, viewHolder.userImageView, "profile")
                context.startActivity(intent, options.toBundle())

            }
        })
    }

    override fun getItemCount() = dataSet.size

    fun setItems(list: ArrayList<Repository>) {
        dataSet = list
    }

}