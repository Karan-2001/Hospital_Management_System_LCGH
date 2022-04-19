package com.example.lion_nav_barhomepage

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lion_nav_barhomepage.Home.HomeFragment
import com.example.lion_nav_barhomepage.databinding.FragmentLoginTabBinding
import com.example.lion_nav_barhomepage.databinding.FragmentRegisterTabBinding
import com.example.lion_nav_barhomepage.patientdashboard.PatientProfileFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginTabFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private var _binding: FragmentLoginTabBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginTabBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signup.setOnClickListener {
            if(checking()){
                val email=binding.email.text.toString()
                val password= binding.signPass.text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this.requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this.context, "Logged in", Toast.LENGTH_LONG).show()
                            val intent = Intent(this.context, MainActivity::class.java)
                                startActivity(intent)
                        } else {
                            Toast.makeText(this.context, "Wrong Details", Toast.LENGTH_LONG).show()
                        }
                    }
            }
            else{
                Toast.makeText(this.context,"Enter the Details",Toast.LENGTH_LONG).show()
            }
        }
    }



 fun checking():Boolean
{
    if(binding.email.text.toString().trim{it<=' '}.isNotEmpty()
        && binding.signPass.text.toString().trim{it<=' '}.isNotEmpty())
    {
        return true
    }
    return false
}
}








