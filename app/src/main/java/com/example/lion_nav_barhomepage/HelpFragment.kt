package com.example.lion_nav_barhomepage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.lion_nav_barhomepage.Home.HomeFragment
import com.example.lion_nav_barhomepage.Vaccines.NewVaccinesAdapter
import com.example.lion_nav_barhomepage.Vaccines.r_id
import com.example.lion_nav_barhomepage.Vaccines.url_img
import com.example.lion_nav_barhomepage.databinding.FragmentHelpBinding
import com.example.lion_nav_barhomepage.databinding.FragmentNewVaccinesBinding
import com.google.firebase.storage.FirebaseStorage


class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.learnMoreButton.setOnClickListener {
            val dialog = CovidDialogFragment()
            dialog.show(parentFragmentManager, "custom")

        }
        binding.indiaHelplineButton.setOnClickListener {
            val dialog = HelplineDialogFragment()
            dialog.show(parentFragmentManager, "custom")
        }
        super.onViewCreated(view, savedInstanceState)
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

    }
}