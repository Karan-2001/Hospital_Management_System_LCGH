package com.example.lion_nav_barhomepage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lion_nav_barhomepage.News.HealthNewsFragment
import com.example.lion_nav_barhomepage.News.LCGHNewsFragment

class NewsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HealthNewsFragment()
            1 -> fragment = LCGHNewsFragment()
        }
        return fragment as Fragment
    }
}