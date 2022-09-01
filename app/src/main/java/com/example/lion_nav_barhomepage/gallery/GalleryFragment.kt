package com.example.lion_nav_barhomepage

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lion_nav_barhomepage.Contact.ContactFragment
import com.example.lion_nav_barhomepage.databinding.FragmentGalleryBinding
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.gallery.ImageFragment
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * Use the [GalleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
var urlimage : String = ""
class GalleryFragment : Fragment(),ImageAdapter.Click,ImageAdapter.Replace {

    private lateinit var recyclerView: RecyclerView
    private lateinit var  photoAdapter: ImageAdapter
    private var dataList = mutableListOf<image>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    var db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title="Gallery"
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(activity,2)

        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Fetching data....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        if (!DoctorsFragment().isConnected(requireContext())) {
            Toast.makeText(requireContext(), "No Internet ", Toast.LENGTH_SHORT).show();
            Log.e("network--->","if-block")
            progressDialog.dismiss()
        }
        db.collection("Gallery").get()
            .addOnSuccessListener { datas ->
                for (i in datas) {
                    Log.e("list", "${i}")
                    val obj = image(
                        i.data["image"].toString(),
                        i.data["text"].toString()
                    )

                    dataList.add(obj)
                }

                recyclerView.adapter = ImageAdapter(dataList as ArrayList<image>,this@GalleryFragment,this@GalleryFragment)
                progressDialog.dismiss()


            }
            .addOnFailureListener { exception ->
                Log.e("---->", "Error getting documents: ")
            }

    }
    override fun go_to_main_view(url: String) {
        urlimage = url
       replaceFragment(ImageFragment())
    }
    override fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }




}
