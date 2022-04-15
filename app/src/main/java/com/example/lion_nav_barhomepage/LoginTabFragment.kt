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
                            replaceFragment(HomeFragment())
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






    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.constraint, fragment)
        fragmentTransaction.commit()

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








//private fun doLogin() {
//    if (binding.email.text.toString().isEmpty()) {
//        binding.email.error = "Please enter email"
//        binding.email.requestFocus()
//        return
//    }
//
//    if (!Patterns.EMAIL_ADDRESS.matcher(binding.email.text.toString()).matches()) {
//        binding.email.error = "Please enter valid email"
//        binding.email.requestFocus()
//        return
//    }
//
//    if (binding.signPass.text.toString().isEmpty()) {
//        binding.signPass.error = "Please enter password"
//        binding.signPass.requestFocus()
//        return
//    }
//
//    auth.signInWithEmailAndPassword(binding.email.text.toString(), binding.signPass.text.toString())
//        .addOnCompleteListener(this.requireActivity()) { task ->
//            if (task.isSuccessful) {
//                val user = auth.currentUser
//                updateUI(user)
//            } else {
//
//                updateUI(null)
//            }
//        }
//}
//
//override fun onStart() {
//    super.onStart()
//    val currentUser = auth.currentUser
//    updateUI(currentUser)
//}
//
//private fun updateUI(currentUser: FirebaseUser?) {
//
//    if (currentUser != null) {
//        if(currentUser.isEmailVerified) {
//            Toast.makeText(
//                context, "current",
//                Toast.LENGTH_SHORT
//            ).show()
//        }else{
//            Toast.makeText(
//                context, "Please verify your email address.",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    } else {
//        Toast.makeText(
//            context, "Login failed.",
//            Toast.LENGTH_SHORT
//        ).show()
//    }
//}

