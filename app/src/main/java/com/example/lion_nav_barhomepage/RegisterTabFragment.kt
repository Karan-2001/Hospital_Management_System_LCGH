package com.example.lion_nav_barhomepage

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.lion_nav_barhomepage.databinding.FragmentDoctorsBinding
import com.example.lion_nav_barhomepage.databinding.FragmentRegisterTabBinding
import com.google.firebase.auth.FirebaseAuth

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
      private var _binding: FragmentRegisterTabBinding? = null
       private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
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
        binding.signup.setOnClickListener {
            signUpUser()
        }
        super.onViewCreated(view, savedInstanceState)

    }
    fun signUpUser() {
        if (binding.signName.text.toString().isEmpty()) {
            binding.signName.error = "Please enter email"
            binding.signName.requestFocus()
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
        if (binding.signCpass.text.toString()!=binding.signPass.text.toString()) {
            binding.signCpass.error = "password not the same"
            binding.signCpass.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(binding.signEmail.text.toString(), binding.signPass.text.toString())
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    replaceFragment(LoginTabFragment())

                } else {
                    Toast.makeText(context, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }

}