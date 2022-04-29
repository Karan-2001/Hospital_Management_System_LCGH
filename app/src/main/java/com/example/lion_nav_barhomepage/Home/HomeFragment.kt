package com.example.lion_nav_barhomepage.Home

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.lion_nav_barhomepage.Appointment.AppointmentFragment
import com.example.lion_nav_barhomepage.Facilities.FacilitiesFragment
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentHomeBinding
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.patient_main_data

import com.google.android.material.appbar.CollapsingToolbarLayout

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
    private lateinit var animationDrawable: AnimationDrawable




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
        if( patient_main_data.name != null){
            binding.pName.setText("Hello "+ patient_main_data.name.toString())
        }
        else{
            binding.pName.setText("Hello!!")
        }

        binding.b1.setOnClickListener {
            replaceFragment(DoctorsFragment())
        }
        binding.b2.setOnClickListener {
            replaceFragment(AppointmentFragment())
        }
        binding.b3.setOnClickListener {
            replaceFragment(FacilitiesFragment())
        }

        val collapsingToolbarLayout: CollapsingToolbarLayout = binding.collapsingToolbar
        val animationDrawable = collapsingToolbarLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.start()


    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
    }



    }
