package com.amar.testapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amar.testapp.R
import com.amar.testapp.activities.RepoDetailsActivity
import com.amar.testapp.network.Repository
import java.text.SimpleDateFormat

class CustomAdapter(private val dataSet: List<Repository>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nameTV: TextView
        val dateTV: TextView

        init {
            nameTV = view.findViewById(R.id.nameTV)
            dateTV = view.findViewById(R.id.dateTV)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.listview_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameTV.text = dataSet.get(position).name

        val format = "yyyy-MM-dd'T'HH:mm:ssX"
        val date1 = SimpleDateFormat(format).parse(dataSet.get(position).updatedAt)
        val format2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        viewHolder.dateTV.text = format2.format(date1)

        viewHolder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(viewHolder.itemView.context, RepoDetailsActivity::class.java)
                intent.putExtra("repo", dataSet.get(position))
                viewHolder.itemView.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount() = dataSet.size

}