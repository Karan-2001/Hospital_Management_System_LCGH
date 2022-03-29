package com.example.lion_nav_barhomepage.about

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example.lion_nav_barhomepage.databinding.FragmentAboutBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentAboutBinding? = null
    private val binding get()=_binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.aboutBtn?.setOnClickListener{
             var dialog = CustomDialogFragment()
            dialog.show(childFragmentManager,"custom")
           // val myDialogView=LayoutInflater.from(this).inflate(R.layout.about_us_dialog,null)
           // val mBuilder = AlertDialog.Builder(requireContext())
           //     .setView(myDialogView)
           // val mAlertDialog = mBuilder.show()
          //  mAlertDialog.dismiss()

        }
    }




}