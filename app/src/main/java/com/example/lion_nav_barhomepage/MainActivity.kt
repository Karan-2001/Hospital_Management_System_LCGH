package com.example.lion_nav_barhomepage

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.lion_nav_barhomepage.Appointment.AppointmentFragment
import com.example.lion_nav_barhomepage.Contact.ContactFragment
import com.example.lion_nav_barhomepage.Covid.CovidFragment
import com.example.lion_nav_barhomepage.Facilities.FacilitiesFragment
import com.example.lion_nav_barhomepage.Gallery.GalleryFragment
import com.example.lion_nav_barhomepage.Home.HomeFragment
import com.example.lion_nav_barhomepage.about.AboutFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsProfileFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(HomeFragment(), "Home")

        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_Doctors -> replaceFragment(DoctorsFragment(), it.title.toString())
                R.id.nav_appointments -> replaceFragment(AppointmentFragment(), it.title.toString())
                R.id.nav_facilities -> replaceFragment(FacilitiesFragment(), it.title.toString())
                R.id.nav_Contact -> replaceFragment(ContactFragment(), it.title.toString())
                R.id.nav_gallery -> replaceFragment(GalleryFragment(), it.title.toString())
                R.id.nav_covid -> replaceFragment(CovidFragment(), it.title.toString())
                R.id.nav_logout -> Toast.makeText(
                    applicationContext,
                    "logging out from account",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_about -> replaceFragment(AboutFragment(), it.title.toString())

            }
            true
        }
    }

    fun replaceFragment(fragment: Fragment, title: String) {
        val navView: NavigationView = findViewById(R.id.nav_view)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        //fragmentTransaction.addToBackStack("home")
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
//        setTitle(title)
        when (title) {
            "Home" -> navView.setCheckedItem(R.id.nav_home)
            "Doctors" -> navView.setCheckedItem(R.id.nav_Doctors)
            "Facilities" -> navView.setCheckedItem(R.id.nav_facilities)
            "Covid Corner" -> navView.setCheckedItem(R.id.nav_covid)
            "About Us" -> navView.setCheckedItem(R.id.nav_about)
            "Contact Us" -> navView.setCheckedItem(R.id.nav_Contact)
            "Gallery" -> navView.setCheckedItem(R.id.nav_gallery)
            "Book Appointment" -> navView.setCheckedItem(R.id.nav_appointments)
            "Doctor Profile" -> navView.setCheckedItem(R.id.nav_facilities)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return true
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.framelayout)) {

            is DoctorsProfileFragment -> replaceFragment(DoctorsFragment(), "Doctors")
            !is HomeFragment -> replaceFragment(HomeFragment(), "Home")
            else -> super.onBackPressed()

        }
//        override fun onBackPressed() {
//            val count = supportFragmentManager.backStackEntryCount
//            if (count == 0) {
//                super.onBackPressed()
//                //additional code
//            } else {
//                supportFragmentManager.popBackStack()
//            }
    }
}





