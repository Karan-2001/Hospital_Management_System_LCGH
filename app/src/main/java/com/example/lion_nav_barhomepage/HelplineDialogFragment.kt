package com.example.lion_nav_barhomepage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.lion_nav_barhomepage.databinding.FragmentCovidDialogBinding
import com.example.lion_nav_barhomepage.databinding.FragmentHelplineDialogBinding

class HelplineDialogFragment : DialogFragment() {
    private var _binding: FragmentHelplineDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHelplineDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tel2.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:08912503228")
            startActivity(intent)
        }
        binding.tel1.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+91-866-2410978")
            startActivity(intent)
        }
        binding.back.setOnClickListener {
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}