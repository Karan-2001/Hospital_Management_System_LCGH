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
import com.example.lion_nav_barhomepage.databinding.FragmentDoctorsBinding
import com.example.lion_nav_barhomepage.databinding.FragmentRegisterTabBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth


        private lateinit var db: FirebaseFirestore
    private var _binding: FragmentRegisterTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterTabBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signup.setOnClickListener {
            signUpUser()
        }





    }
    private fun signUpUser() {

        if (binding.signName.text.toString().isEmpty()) {
            binding.signName.error = "Please enter email"
            binding.signName.requestFocus()
            return
        }
        if (binding.signPhone.text.toString().isEmpty()) {
            binding.signPhone.error = "Please enter email"
            binding.signPhone.requestFocus()
            return
        }
        if (binding.signEmail.text.toString().isEmpty()) {
            binding.signEmail.error = "Please enter email"
            binding.signEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding.signEmail.text.toString()).matches()) {
            binding.signEmail.error = "Please enter valid email"
            binding.signEmail.requestFocus()
            return
        }

        if (binding.signPass.text.toString().isEmpty()) {
            binding.signPass.error = "Please enter password"
            binding.signPass.requestFocus()
            return
        }
        var email = binding.signEmail.text.toString()
        var password = binding.signPass.text.toString()
        var name = binding.signName.text.toString()
        var phone = binding.signPhone.text.toString()
        var user = hashMapOf(
            "Name" to name,
            "Phone" to phone,
            "Email" to email
        )
        val users = db.collection("USERS")
        val query = users.whereEqualTo("Email",email).get()

        auth.createUserWithEmailAndPassword(binding.signEmail.text.toString(), binding.signPass.text.toString())
            .addOnSuccessListener {
                users.document(email).set(user)
                Toast.makeText(
                                   this.context, "Registration Successful", Toast.LENGTH_SHORT).show()

                view_Pager.setCurrentItem(1)
            }
            .addOnFailureListener {Toast.makeText(
                        this.context, "Sign Up failed.Email already exists.",
                        Toast.LENGTH_SHORT
                    ).show()  }
//            .addOnCompleteListener(this.requireActivity()) { task ->
//
//                            if (task.isSuccessful) {
////                                Toast.makeText(
////                                    this.context, "Registration Successful", Toast.LENGTH_SHORT).show()
//
//                                val intent = Intent(this.context, MainActivity::class.java)
//                                startActivity(intent)
//                            }
//
//                 else{
//                    Toast.makeText(
//                        this.context, "Sign Up failed. Try again after some time.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
    }
}


