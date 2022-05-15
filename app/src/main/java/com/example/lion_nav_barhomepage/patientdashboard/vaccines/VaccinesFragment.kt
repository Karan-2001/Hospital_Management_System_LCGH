package com.example.lion_nav_barhomepage.patientdashboard.vaccines

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentReportsBinding
import com.example.lion_nav_barhomepage.databinding.FragmentVaccinesBinding
import com.example.lion_nav_barhomepage.patient_main_data
import com.example.lion_nav_barhomepage.patientdashboard.reports.*
import com.google.firebase.firestore.FirebaseFirestore

var vaccine_list : ArrayList<vaccine> =  arrayListOf<vaccine>()
var vaccine_id : String = ""
class VaccinesFragment : Fragment() , VaccineAdapter.Click, VaccineAdapter.replace{

    private lateinit var recyclerView: RecyclerView
    private lateinit var db: FirebaseFirestore
    private var _binding: FragmentVaccinesBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { db = FirebaseFirestore.getInstance()
        _binding = FragmentVaccinesBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title="Vaccines"
        super.onViewCreated(view, savedInstanceState)
        binding.addpres.setOnClickListener {
            replaceFragment(AddVaccineFragment())
        }
        recyclerView = binding.RecyclerView
        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Fetching data....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        vaccine_list =arrayListOf<vaccine>()
        db.collection("Vaccine").whereEqualTo("pemial", patient_main_data.email.toString())
            .get()
            .addOnSuccessListener {
                    datas->
                for ( i in datas){
                    Log.e("list","${i}")
                    val obj = vaccine(
                        i.data["vaccine_id"].toString(),
                        i.data["pemial"].toString(),
                        i.data["pname"].toString(),
                        i.data["date"].toString(),
                        i.data["vname"].toString(),
                        i.data["pdf"].toString()
                    )
                    vaccine_list.add(obj)
                }
                recyclerView.adapter = VaccineAdapter(this@VaccinesFragment,
                    vaccine_list,this@VaccinesFragment,this@VaccinesFragment)
                progressDialog.dismiss()
                Log.e("list", "$vaccine_list")
            }.addOnFailureListener { exception ->
                Log.e("---->", "Error getting documents: ")
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
        val filtered: ArrayList<vaccine> = ArrayList()

        val courseAry: ArrayList<vaccine> = vaccine_list

        for (eachCourse in courseAry) {
            if (eachCourse.date!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.date!!.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filtered.add(eachCourse)
            } else if (eachCourse.vname!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.vname!!.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filtered.add(eachCourse)
            }
            else if (eachCourse.vaccine_id!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.vaccine_id!!.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filtered.add(eachCourse)
//
            }

            //calling a method of the adapter class and passing the filtered list
//        DoctorsAdapter(this,filtered)
            recyclerView.adapter = VaccineAdapter(
                this@VaccinesFragment,
                filtered, this@VaccinesFragment, this@VaccinesFragment
            )
        }
    }
    override fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()
    }

    override fun go_to_main_view(url: String, r_id: String) {
        vaccine_id =r_id
        pdfactivity().seturl(url,2)
    }
}