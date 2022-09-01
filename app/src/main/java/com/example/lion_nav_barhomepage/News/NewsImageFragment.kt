package com.example.lion_nav_barhomepage.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import coil.load
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.*
import com.example.lion_nav_barhomepage.urlimage

class NewsImageFragment : Fragment() {

    private var _binding: ActivityNewsClassBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ActivityNewsClassBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.largeImage
            .load(newsimage.toUri()){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
                super.onViewCreated(view, savedInstanceState)
                binding.enquire.setOnClickListener{
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:08912503228")
                    startActivity(intent)
                }


        }
    }
}
