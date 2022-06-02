package com.example.lion_nav_barhomepage.Gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentGalleryBinding
import com.example.lion_nav_barhomepage.patientdashboard.MedicinesFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
private var _binding:FragmentGalleryBinding? = null
private val binding get() = _binding!!




/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GalleryFragment : Fragment(), AdapterView.OnItemClickListener {
    // TODO: Rename and change types of parameters
    private var gridView: GridView?= null
    private var imglist: ArrayList<image>? = null
    private var imageadapter:ImageAdapter? = null


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
        imglist= ArrayList()
        imglist=setData()
        gridView?.adapter = imglist?.let { ImageAdapter(this, it) }
        gridView!!.onItemClickListener = this
        (activity as AppCompatActivity).supportActionBar?.title="Gallery"
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

    private fun setData(): ArrayList<image>? {
        var imagelist: ArrayList<image> = ArrayList()
        imagelist.add(image(R.drawable.img1))
        imagelist.add(image(R.drawable.img2))
        imagelist.add(image(R.drawable.imge))
        imagelist.add(image(R.drawable.img4))
        imagelist.add(image(R.drawable.img5))

        return imagelist
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var items: image = imglist!!.get(p2)
        replaceFragment(MedicinesFragment())
    }
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }


}
