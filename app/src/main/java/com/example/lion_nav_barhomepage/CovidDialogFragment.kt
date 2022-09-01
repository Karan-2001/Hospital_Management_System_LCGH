package com.example.lion_nav_barhomepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.lion_nav_barhomepage.databinding.FragmentCovidDialogBinding
import com.example.lion_nav_barhomepage.databinding.FragmentHelpBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CovidDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CovidDialogFragment : DialogFragment() {
    private var _binding: FragmentCovidDialogBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCovidDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }
}