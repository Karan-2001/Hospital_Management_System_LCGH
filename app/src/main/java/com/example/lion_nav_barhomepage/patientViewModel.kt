package com.example.lion_nav_barhomepage

import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class patientViewModel:ViewModel(){


//    var id: Int = 0
//    var name:String = ""
//    var email: String = ""
//    var phone:String = ""
//    var img_url:String = ""
//    var gender:String = ""
//    var DOB: String = ""
    var patient_info =patientdata()

    var db_patient = FirebaseFirestore.getInstance()
    fun set_patientdata(): patientdata {

        val loggedEmail= sharedPreferences.getString("Email",null)
        Log.e("patient logged email::","${loggedEmail}")
        if(loggedEmail != null){
            val docRef = db_patient.collection("USERS").document(loggedEmail.toString())
            docRef.get()
                .addOnSuccessListener {
                    task->
                    Log.e("patient -->email: ","${task.get("Email").toString()}")

                   patient_info = patientdata(
                       task.get("Id").toString(),
                      task.get("Name").toString(),
                       task.get("Email").toString(),
                       task.get("Phone").toString(),
                       task.get("Img_url").toString(),
                       task.get("Gender").toString(),
                       task.get("Dob").toString())
                    Log.e(" data object ",">>>>> ${patient_info}")

                }
                .addOnFailureListener{
                    Log.e("patient data ",">>>>> FAIlED")
                }


        }
        return patient_info

    }

}