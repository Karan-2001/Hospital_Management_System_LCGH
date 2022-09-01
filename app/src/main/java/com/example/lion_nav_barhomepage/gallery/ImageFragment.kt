package com.example.lion_nav_barhomepage.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import coil.load
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.ActivityZoomClassBinding
import com.example.lion_nav_barhomepage.databinding.FragmentDoctorsBinding
import com.example.lion_nav_barhomepage.databinding.FragmentHomeBinding
import com.example.lion_nav_barhomepage.databinding.FragmentImageBinding
import com.example.lion_nav_barhomepage.urlimage

class ImageFragment : Fragment() {

    private var _binding: ActivityZoomClassBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ActivityZoomClassBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.largeImage
            .load(urlimage.toUri()){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
                super.onViewCreated(view, savedInstanceState)


        }
    }
}
