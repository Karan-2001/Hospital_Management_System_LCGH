package com.example.lion_nav_barhomepage.patientdashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.DoctorsProfileBinding
import com.example.lion_nav_barhomepage.databinding.FragmentPatientProfileBinding
import com.example.lion_nav_barhomepage.doctors.DoctorsAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PatientProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PatientProfileFragment : Fragment() {
    private var _binding: FragmentPatientProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPatientProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title="Patient Profile"
        binding.app.setOnClickListener{
            replaceFragment(PatientAppointmentsFragment())
        }
        binding.vaccine.setOnClickListener{
            replaceFragment(VaccinesFragment())
        }
        binding.results.setOnClickListener{
            replaceFragment(ReportsFragment())
        }
        binding.history.setOnClickListener{
            replaceFragment(HistoryFragment())
        }
        binding.diagnosis.setOnClickListener{
            replaceFragment(DiagnosisFragment())
        }
        binding.medicine.setOnClickListener{
            replaceFragment(MedicinesFragment())
        }
        binding.editButton.setOnClickListener {
            replaceFragment(EditProfileFragment())
        }
        super.onViewCreated(view, savedInstanceState)

    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }

}