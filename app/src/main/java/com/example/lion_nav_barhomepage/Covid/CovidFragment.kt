package com.example.lion_nav_barhomepage.Covid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.lion_nav_barhomepage.CovidPagerAdapter
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.SectionPagerAdapter
import com.example.lion_nav_barhomepage.databinding.FragmentCovidBinding
import com.example.lion_nav_barhomepage.databinding.FragmentVaccinesBinding
import com.example.lion_nav_barhomepage.patientdashboard.vaccines.VaccinesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CovidFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CovidFragment : Fragment(R.layout.fragment_covid) {
    private lateinit var binding: FragmentCovidBinding

    companion object {
        @StringRes
        private val CTAB_TITLES = intArrayOf(

            R.string.livedata,
            R.string.help
        )
    }





    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title="Covid Corner"
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCovidBinding.bind(view)

        val sectionPagerAdapter = CovidPagerAdapter(this)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(CovidFragment.CTAB_TITLES[position])
        }.attach()
    }


}