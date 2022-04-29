package com.example.lion_nav_barhomepage.patientdashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentAppointmentBinding
import com.example.lion_nav_barhomepage.databinding.FragmentPatientAppointmentsBinding
import com.google.firebase.firestore.FirebaseFirestore

class PatientAppointmentsFragment : Fragment() {

    private lateinit var db: FirebaseFirestore
    private var _binding: FragmentPatientAppointmentsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = FirebaseFirestore.getInstance()
        _binding = FragmentPatientAppointmentsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title="Appointments"
        super.onViewCreated(view, savedInstanceState)
        binding.button3.setOnClickListener {
            Log.e("dsdsdsd","clicked")
           db.collection("USERS").whereGreaterThan("id","3")
               .get()
               .addOnSuccessListener {
               datas->
               Log.e("dsdsdsd","datafetched")
               for ( i in datas){

                   Log.e("${i.id}" ,"=> ${i.data["id"]}")
               }
           }
               .addOnFailureListener { exception ->
               Log.e("---->","Error getting documents: ")
           }

        }
    }


}