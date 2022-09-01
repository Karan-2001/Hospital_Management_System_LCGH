package com.example.lion_nav_barhomepage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CovidPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {

            0-> fragment = LiveMapFragment()
            1 -> fragment = HelpFragment()
        }
        return fragment as Fragment
    }
}