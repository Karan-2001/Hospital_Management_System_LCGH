package com.example.lion_nav_barhomepage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.example.lion_nav_barhomepage.Appointment.AppointmentFragment
import com.example.lion_nav_barhomepage.Contact.ContactFragment
import com.example.lion_nav_barhomepage.Covid.CovidFragment
import com.example.lion_nav_barhomepage.Facilities.FacilitiesFragment
import com.example.lion_nav_barhomepage.Gallery.GalleryFragment
import com.example.lion_nav_barhomepage.Home.HomeFragment
import com.example.lion_nav_barhomepage.about.AboutFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsProfileFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsViewModel
import com.example.lion_nav_barhomepage.patientdashboard.*
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         val viewModel: patientViewModel by viewModels()

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerLayout = navView.getHeaderView(0)

        val nav_drawer_header = headerLayout.findViewById(R.id.profile) as LinearLayout
        val username = headerLayout.findViewById(R.id.user_name) as TextView
        val useremail = headerLayout.findViewById(R.id.user_email) as TextView
//        viewModel.set_patientdata()
        Log.e("In main ::","${patientData}")
        username.setText(patientData.name.toString())
        useremail.setText(patientData.email.toString())
        nav_drawer_header.setOnClickListener{
            replaceFragment(PatientProfileFragment(),"Patient Profile")
        }
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        replaceFragment(HomeFragment(), "Home")

        navView.setNavigationItemSelectedListener {
            setcheckbutton(it)
            when (it.itemId) {
                R.id.nav_home -> replaceFragment(HomeFragment(), it.title.toString())
                R.id.nav_Doctors -> replaceFragment(DoctorsFragment(), it.title.toString())
                R.id.nav_appointments -> replaceFragment(AppointmentFragment(), it.title.toString())
                R.id.nav_facilities -> replaceFragment(FacilitiesFragment(), it.title.toString())
                R.id.nav_Contact -> replaceFragment(ContactFragment(), it.title.toString())
                R.id.nav_gallery -> replaceFragment(GalleryFragment(), it.title.toString())
                R.id.nav_covid -> replaceFragment(CovidFragment(), it.title.toString())
                R.id.nav_logout -> logout()

                R.id.nav_about -> replaceFragment(AboutFragment(), it.title.toString())

            }
            true
        }
    }

    private fun logout() {
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("logged",false).apply()
        Toast.makeText(
            applicationContext,
            "logging out from account",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
    }

    private fun setcheckbutton(menuItem: MenuItem) {
        menuItem.isChecked=false
        val id = supportFragmentManager.findFragmentById(R.id.framelayout )
        val navView: NavigationView = findViewById(R.id.nav_view)
        when (id) {
//            is PatientProfileFragment -> menuItem.isChecked=false
            is HomeFragment -> navView.setCheckedItem(R.id.nav_home)
            is DoctorsFragment -> navView.setCheckedItem(R.id.nav_Doctors)
            is DoctorsProfileFragment -> navView.setCheckedItem(R.id.nav_Doctors)
            is FacilitiesFragment -> navView.setCheckedItem(R.id.nav_facilities)
            is CovidFragment -> navView.setCheckedItem(R.id.nav_covid)
            is AboutFragment -> navView.setCheckedItem(R.id.nav_about)
            is ContactFragment -> navView.setCheckedItem(R.id.nav_Contact)
            is GalleryFragment -> navView.setCheckedItem(R.id.nav_gallery)
            is AppointmentFragment -> navView.setCheckedItem(R.id.nav_appointments)
            else -> menuItem.isChecked=false
        }

    }

    fun replaceFragment(fragment: Fragment, title: String) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        //fragmentTransaction.addToBackStack("home")
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
//        setTitle(title)
//
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return true
    }

    override fun onBackPressed() {
        val id = supportFragmentManager.findFragmentById(R.id.framelayout )
        if( id  is PatientAppointmentsFragment ||
            id  is  VaccinesFragment||
            id  is ReportsFragment ||
            id  is HistoryFragment ||
            id  is  DiagnosisFragment||
            id  is MedicinesFragment ||
            id is EditProfileFragment    ){
            replaceFragment(PatientProfileFragment(),"Patient Profile")
        }
        else if(id is DoctorsProfileFragment){
            replaceFragment(DoctorsFragment(), "Doctors")

        }
        else if (id !is HomeFragment){
            replaceFragment(HomeFragment(), "Home")
        }
        else {super.onBackPressed()}

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





