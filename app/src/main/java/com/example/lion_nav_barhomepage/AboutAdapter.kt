package com.example.lion_nav_barhomepage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lion_nav_barhomepage.about.AboutUsFragment


class AboutAdapter(var aboutList: ArrayList<aboutmodel>,  val context: AboutUsFragment) : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {








    // Provide a direct reference to each of the views with data items

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var text: TextView =itemView.findViewById(R.id.title)


    }

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AboutAdapter.ViewHolder {

        // Inflate the custom layout
        var view = LayoutInflater.from(parent.context).inflate(R.layout.about_item, parent, false)
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: AboutAdapter.ViewHolder, position: Int) {

        // Get the data model based on position
        var data = aboutList[position]

        // Set item views based on your views and data model


        holder.text.text = data.text
    }

    //  total count of items in the list
    override fun getItemCount() = aboutList.size




}