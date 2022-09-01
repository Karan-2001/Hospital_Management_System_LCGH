package com.example.lion_nav_barhomepage.News

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.lion_nav_barhomepage.NewsAdapter
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.SectionPagerAdapter
import com.example.lion_nav_barhomepage.databinding.FragmentNews2Binding
import com.example.lion_nav_barhomepage.databinding.FragmentVaccinesBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsFragment : Fragment(R.layout.fragment_news2) {
    private lateinit var binding: FragmentNews2Binding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.ntab1,
            R.string.ntab2
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title="News"
        binding = FragmentNews2Binding.bind(view)

        val NewsAdapter = NewsAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = NewsAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}