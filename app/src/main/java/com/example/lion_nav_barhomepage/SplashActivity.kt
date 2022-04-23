package com.example.lion_nav_barhomepage

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import java.lang.Thread.sleep
var patientData = patientdata()
@Suppress("DEPRECATION")

class SplashActivity : AppCompatActivity() {
    val viewModel: patientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE)
        patientData=viewModel.set_patientdata()

        val checklogin = sharedPreferences.getBoolean("logged", false)
        if (checklogin == true) {

            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3500)
        }
        else {
            Handler().postDelayed({
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }, 3500)

        }
    }
}