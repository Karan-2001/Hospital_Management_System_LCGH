package com.example.lion_nav_barhomepage.Gallery

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.View
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.widget.ImageView
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.context_activity
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.data

class ImageAdapter(val context: GalleryFragment, var img_list: ArrayList<image>) : BaseAdapter() {


    override fun getCount(): Int {
        return img_list.size
    }

    override fun getItem(i: Int): Any {
        return img_list.get(i)
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        var view:View=View.inflate(context_activity, R.layout.gallaryimage,null)
       var img:ImageView=view.findViewById(R.id.image)
        var listItems: image =img_list.get(i)
    img.setImageResource(listItems.img_url!!.toInt())

        return view
    }
}

