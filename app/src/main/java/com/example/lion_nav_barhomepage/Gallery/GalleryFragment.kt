package com.example.lion_nav_barhomepage.Gallery

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentGalleryBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
private var _binding:FragmentGalleryBinding? = null
private val binding get() = _binding!!
private lateinit var gridView: GridView
var data_img: ArrayList<Int> = arrayListOf(
    R.drawable.img1,R.drawable.img2,R.drawable.imge,R.drawable.img4,R.drawable.img5
)

/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //arguments?.let {
        // param1 = it.getString(ARG_PARAM1)
        //  param2 = it.getString(ARG_PARAM2)
        //   }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        gridView = binding.PhotoGrid
        gridView.adapter = ImageAdapter(data_img, this)
        // gridView.setOnItemClickListener(AdapterView.OnItemClickListener(){
        //  fun onItemClick(
         // parent: AdapterView<*>,
       //  view: View,
         // position: Int,
         //  id: Long
         //  ){
           //  val item_pos: Int = data.get(position)
       //   })
        //  }


        //fun ShowDialogBox(item_position : Int){
        // var dialog = Dialog(this)
        //dialog.setContentView(R.layout.)
        //   }
        //   }

        // }


    }
}
        // companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GalleryFragment.
         */
        //  // TODO: Rename and change types and number of parameters
        //  @JvmStatic
        //  fun newInstance(param1: String, param2: String) =
        //  GalleryFragment().apply {
        //   arguments = Bundle().apply {
        //    putString(ARG_PARAM1, param1)
        //   putString(ARG_PARAM2, param2)
        //  }
        //  }
        // }
