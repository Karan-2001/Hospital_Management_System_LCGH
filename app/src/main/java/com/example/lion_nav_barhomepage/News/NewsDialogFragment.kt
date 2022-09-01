package com.example.lion_nav_barhomepage.News

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import coil.load
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.ActivityNewsClassBinding
import com.example.lion_nav_barhomepage.databinding.FragmentCovidDialogBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsDialogFragment : DialogFragment() {
    private var _binding: ActivityNewsClassBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityNewsClassBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.exit.setOnClickListener {
            dismiss()
        }
        binding.largeImage
            .load(newsimage.toUri()) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
                super.onViewCreated(view, savedInstanceState)
                binding.enquire.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:08912503228")
                    startActivity(intent)
                }
                super.onViewCreated(view, savedInstanceState)
            }
    }
}

