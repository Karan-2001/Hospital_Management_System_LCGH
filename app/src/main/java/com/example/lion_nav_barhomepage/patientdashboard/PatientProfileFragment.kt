package com.example.lion_nav_barhomepage.patientdashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.example.lion_nav_barhomepage.IntroActivity
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentPatientProfileBinding
import com.example.lion_nav_barhomepage.patient_main_data
import com.example.lion_nav_barhomepage.patientdashboard.appointment.PatientAppointmentsFragment
import com.example.lion_nav_barhomepage.patientdashboard.diagnosis.DiagnosisFragment
import com.example.lion_nav_barhomepage.patientdashboard.reports.ReportsFragment
import com.example.lion_nav_barhomepage.patientdashboard.vaccines.VaccinesFragment
import com.example.lion_nav_barhomepage.sharedPreferences

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

        binding.diagnosis.setOnClickListener{
            replaceFragment(DiagnosisFragment())
        }

        binding.editButton.setOnClickListener {
            replaceFragment(EditProfileFragment())
        }
        binding.logoutButton.setOnClickListener {
            logout()
        }
        binding.pname.setText( patient_main_data.name)
        binding.pemail.setText( patient_main_data.email)
        binding.pid.setText( patient_main_data.id)
        val img_url= patient_main_data.img_url.toString()
        if (img_url == ""){
            binding.pimg.setImageResource(R.drawable.user_icon)
        }
        else {
            binding.pimg
                .load(img_url?.toUri()) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
        }
        super.onViewCreated(view, savedInstanceState)

    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }
    private fun logout() {
        sharedPreferences = this.requireActivity().getSharedPreferences("login", AppCompatActivity.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("logged",false).apply()
        Toast.makeText(
            context,
            "logging out from account",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this.context, IntroActivity::class.java)
        startActivity(intent)
    }

}