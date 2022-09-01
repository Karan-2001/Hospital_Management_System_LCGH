package com.example.lion_nav_barhomepage.News

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lion_nav_barhomepage.ImageAdapter
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.VaccineRequestFragment
import com.example.lion_nav_barhomepage.VaccineRequestsAdapter
import com.example.lion_nav_barhomepage.Vaccines.bookvaccine

class LCGHNewsAdapter(val context: LCGHNewsFragment, private var news_list: ArrayList<lcghnews>,private val view: LCGHNewsAdapter.Click,):
    RecyclerView.Adapter<LCGHNewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val news_title = itemView.findViewById<TextView>(R.id.newstitle)
        val news_desc = itemView.findViewById<TextView>(R.id.newsdescription)
        val image = itemView.findViewById<ImageView>(R.id.newsimg)

    }
    override fun onBindViewHolder(p0: LCGHNewsAdapter.ViewHolder, p1: Int) {
        p0?.news_title?.text =  news_list[p1].title
        p0?.news_desc?.text = news_list[p1].description
        p0?.image?.load(news_list[p1].image?.toUri()){
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)

        }
        p0.image.setOnClickListener {
            news_list[p1].image?.let { it1 -> view.go_to_main_view(it1) }
        }

    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LCGHNewsAdapter.ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.lcghnews_item, p0, false)
        return LCGHNewsAdapter.ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return news_list.size
    }
    interface Click{
        fun go_to_main_view(url : String)
        fun replaceFragment(fragment: Fragment)
    }



}