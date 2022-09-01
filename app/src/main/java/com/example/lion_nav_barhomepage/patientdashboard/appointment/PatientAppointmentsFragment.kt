package com.example.lion_nav_barhomepage.patientdashboard.appointment

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lion_nav_barhomepage.Appointment.appointment
import com.example.lion_nav_barhomepage.databinding.FragmentPatientAppointmentsBinding
import com.example.lion_nav_barhomepage.doctors.DoctorList
import com.example.lion_nav_barhomepage.doctors.DoctorsAdapter
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.data
import com.example.lion_nav_barhomepage.patient_main_data
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.reflect.typeOf
 lateinit  var appointmentlist : ArrayList<appointment>
class PatientAppointmentsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
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
        recyclerView = binding.RecyclerView
        (activity as AppCompatActivity).supportActionBar?.title="Appointments"
        super.onViewCreated(view, savedInstanceState)
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Fetching data....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        appointmentlist = arrayListOf<appointment>()
        if (!DoctorsFragment().isConnected(requireContext())) {
            Toast.makeText(requireContext(), "No Internet ", Toast.LENGTH_SHORT).show();
            Log.e("network--->","if-block")
            progressDialog.dismiss()
        }
           db.collection("Appointment").whereEqualTo("patient_emial", patient_main_data.email.toString())
               .get()
               .addOnSuccessListener {
               datas->

               for ( i in datas){
                       Log.e("list","${i}")
                       val obj = appointment(
                           i.data["appointment_id"].toString(),
                           i.data["name"].toString(),
                           i.data["spec"].toString(),
                           i.data["patient_emial"].toString(),
                           i.data["date"].toString(),
                           i.data["timeslot"].toString(),
                           i.data["status"].toString()
                       )
                       appointmentlist.add(obj)
               }
                   recyclerView.adapter = AppointmentAdapter(this@PatientAppointmentsFragment, appointmentlist)
                   progressDialog.dismiss()
                   Log.e("list","$appointmentlist")
           }
               .addOnFailureListener { exception ->
               Log.e("---->","Error getting documents: ")
           }
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
    fun filter(text: String) {
        val filtered: ArrayList<appointment> = ArrayList()

        val courseAry: ArrayList<appointment> = appointmentlist

        for (eachCourse in courseAry) {
            if (eachCourse.date!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.spec!!.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filtered.add(eachCourse)
            }
            else if( eachCourse.name!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.spec!!.toLowerCase()
                    .contains(text.toLowerCase())){
                filtered.add(eachCourse)
            }
            else if( eachCourse.status!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.spec!!.toLowerCase()
                    .contains(text.toLowerCase())){
                filtered.add(eachCourse)
            }
            else if( eachCourse.timeslot!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.spec!!.toLowerCase()
                    .contains(text.toLowerCase())){
                filtered.add(eachCourse)
            }
            else if( eachCourse.appointment_id!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.spec!!.toLowerCase()
                    .contains(text.toLowerCase())){
                filtered.add(eachCourse)
            }
        }

        //calling a method of the adapter class and passing the filtered list
//        DoctorsAdapter(this,filtered)
        recyclerView.adapter = AppointmentAdapter(this@PatientAppointmentsFragment, filtered)
    }


}