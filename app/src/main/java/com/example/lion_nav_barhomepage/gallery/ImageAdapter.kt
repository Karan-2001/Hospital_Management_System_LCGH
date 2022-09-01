package com.example.lion_nav_barhomepage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load

class ImageAdapter(var dataList: ArrayList<image>,private val view: Click,private val rep: Replace) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {







    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView= itemView.findViewById(R.id.image)
        val card : CardView = itemView.findViewById(R.id.card)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {


        var view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {


        var data = dataList[position]
        holder.card.setOnClickListener {
            view.go_to_main_view(dataList[position].image)
        }

        holder.image.load(dataList[position].image?.toUri()){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }


    override fun getItemCount() = dataList.size

    interface Click{
        fun go_to_main_view(url : String)

    }
    interface Replace{
        fun replaceFragment(fragment: Fragment)
    }
}