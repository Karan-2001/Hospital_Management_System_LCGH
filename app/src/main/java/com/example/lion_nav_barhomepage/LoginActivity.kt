package com.example.lion_nav_barhomepage

import android.annotation.SuppressLint
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstancesState: Bundle?) {
        super.onCreate(savedInstancesState)
        setContentView(R.layout.activity_login)

        val v=0.0f
        val tabLayout: TabLayout = findViewById(R.id.tab)
        val viewPager: ViewPager = findViewById(R.id.viewpage)
        val fb: FloatingActionButton = findViewById(R.id.facebook)
        val google: FloatingActionButton = findViewById(R.id.google_logo);
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.addTab(tabLayout.newTab().setText("Login"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        val adapter = LoginAdapter(
            this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout)
        )
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        fb.setTranslationY(300F)
        google.setTranslationY(300F)
        tabLayout.setTranslationY(300F)
        fb.setAlpha(v)
        google.setAlpha(v)
        tabLayout.setAlpha(v)

        fb.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()
        google.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()
        tabLayout.animate().translationY(0F).alpha(1F).setDuration(1000).setStartDelay(400).start()

    }
}
