package com.example.lion_nav_barhomepage

import android.content.Context
import android.view.View
import android.widget.BaseAdapter
import android.view.ViewGroup
import android.widget.ImageView

class ImageAdapter(data: ArrayList<Int>, galleryFragment: GalleryFragment) : BaseAdapter() {
    private var myThumbIds: List<Int>? = null
    private var mContext: Context? = null
    fun ImageAdapter(myThumbIds: List<Int>?, mContext: Context?) {
        this.mContext = mContext
        this.myThumbIds = myThumbIds
    }

    override fun getCount(): Int {
        return myThumbIds!!.size
    }

    override fun getItem(i: Int): Any {
        return 0
    }

    override fun getItemId(i: Int): Long {
        return myThumbIds!![i].toLong()
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        var imageView = view as ImageView
        if (imageView == null) {
            imageView = ImageView(mContext)
            imageView.layoutParams = ViewGroup.LayoutParams(350, 450)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        }
        imageView.setImageResource(myThumbIds!![i])
        return imageView
    }
}