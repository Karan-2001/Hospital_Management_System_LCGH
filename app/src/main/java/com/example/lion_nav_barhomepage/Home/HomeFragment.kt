package com.example.lion_nav_barhomepage.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.lion_nav_barhomepage.Appointment.AppointmentFragment
import com.example.lion_nav_barhomepage.Facilities.FacilitiesFragment
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentDoctorsBinding
import com.example.lion_nav_barhomepage.databinding.FragmentHomeBinding
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.google.android.material.navigation.NavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var imageSlider: ImageSlider



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // arguments?.let {
        //    param1 = it.getString(ARG_PARAM1)
        //   param2 = it.getString(ARG_PARAM2)
        // }
        }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
            imageSlider=binding.imageSlider
            val imagelist = ArrayList<SlideModel>()

            imagelist.add(SlideModel(R.drawable.one))
            imagelist.add(SlideModel(R.drawable.img1))
            imagelist.add(SlideModel(R.drawable.img5))
            imageSlider.setImageList(imagelist,ScaleTypes.FIT)
            val view = binding.root
            return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        (activity as AppCompatActivity).supportActionBar?.title="Home"
        binding.b1.setOnClickListener {
            replaceFragment(DoctorsFragment())
        }
        binding.b2.setOnClickListener {
            replaceFragment(AppointmentFragment())
        }
        binding.b3.setOnClickListener {
            replaceFragment(FacilitiesFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }



    }
