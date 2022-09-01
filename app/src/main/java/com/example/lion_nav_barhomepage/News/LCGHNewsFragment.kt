package com.example.lion_nav_barhomepage.News

import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.lion_nav_barhomepage.Appointment.appointment
import com.example.lion_nav_barhomepage.HelplineDialogFragment
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.Vaccines.NewVaccinesAdapter
import com.example.lion_nav_barhomepage.Vaccines.newvaccines
import com.example.lion_nav_barhomepage.Vaccines.url_img
import com.example.lion_nav_barhomepage.databinding.FragmentLCGHNewsBinding
import com.example.lion_nav_barhomepage.databinding.FragmentNewVaccinesBinding
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.gallery.ImageFragment
import com.example.lion_nav_barhomepage.urlimage
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LCGHNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
var newsimage : String = ""
class LCGHNewsFragment : Fragment(),LCGHNewsAdapter.Click {
    private lateinit var recyclerView: RecyclerView
    private lateinit var vaccineAdapter: LCGHNewsAdapter
    private var _binding: FragmentLCGHNewsBinding? = null
    private val binding get() = _binding!!
    var db = Firebase.firestore
    lateinit var myview: View


    private var newsList = ArrayList<lcghnews>()
    private var news = ArrayList<lcghnews>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLCGHNewsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "LCGH News"
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerviewnews
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        (recyclerView.layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.HORIZONTAL

        var app = lcghnews(null,null,null,null)
//        val cal: Calendar = Calendar.getInstance()
//        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
//        val currdate = simpleDateFormat.format(cal.time)
//        val dd = currdate.subSequence(0, 2).toString().toInt()
//        val mm = currdate.subSequence(3, 5).toString().toInt()
//        val yyyy = currdate.subSequence(6, 10).toString().toInt()
//        var l = mutableListOf<Int>()
        val progressDialog = ProgressDialog(this.context)
        progressDialog.setMessage("Fetching data....")
        progressDialog.setCancelable(false)
        progressDialog.show()
        if (!DoctorsFragment().isConnected(requireContext())) {
            Toast.makeText(requireContext(), "No Internet ", Toast.LENGTH_SHORT).show();
            Log.e("network--->","if-block")
            progressDialog.dismiss()
        }
        db.collection("LCGHNews").orderBy("date",Query.Direction.DESCENDING).limit(10).get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                    for(data in it.documents){
                        val news : lcghnews? = data.toObject(lcghnews::class.java)
                        if(news!= null){
                            newsList.add(news)
                        }
                    }

//                for (i in newsList){
//                    var txt=i.date.toString()
//                    var date= txt.split('/')
//                    var x =(date[2].toInt()-yyyy)*365+(date[1].toInt()-mm)*30+(date[0].toInt()-dd)
//                    Log.e("x=", "${x}")
//                    l.plus(x)
//                }
//                if (newsList.size >5) {
//                    for (i in 0..5) {
//                        var ind = l.indexOf(l.min())
//                        news.add(newsList[ind])
//                        l.removeAt(ind)
//
//                    }
//                    newsList=news
                }
                vaccineAdapter = LCGHNewsAdapter(this@LCGHNewsFragment, newsList,this@LCGHNewsFragment)
                recyclerView.adapter = vaccineAdapter
                progressDialog.dismiss()


            }
            .addOnFailureListener { exception ->
                Log.e("---->", "Error getting documents: ")
            }
        db.collection("LCGHNews").orderBy("date",Query.Direction.DESCENDING).limit(1).get()
            .addOnSuccessListener {
                if(!it.isEmpty){
                for(data in it.documents){
                    val news : lcghnews? = data.toObject(lcghnews::class.java)
                    if(news!= null){
                        newsList.add(news)
                    }
                }
                    binding.todaysnews.load(newsList[0].image.toString().toUri()) {
                        placeholder(R.drawable.loading_animation)
                        error(R.drawable.ic_broken_image)
                    }

                }



            }
            .addOnFailureListener { exception ->
                Log.e("---->", "Error getting documents: ")
            }
        binding.todaysnews.setOnClickListener{

            val dialogBuilder = AlertDialog.Builder(requireContext())
            dialogBuilder.setMessage("Would you like to enquire about this news?")
                .setCancelable(true)
            dialogBuilder.setPositiveButton("Enquire") { dialog, id ->
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:08912503228")
                startActivity(intent)
            }
            dialogBuilder.setNegativeButton("No") { dialog, id ->
            }
            val alert = dialogBuilder.create()
            alert.show()
        }


    }
    override fun go_to_main_view(url: String) {
        newsimage = url
        val dialog = NewsDialogFragment()
        dialog.show(parentFragmentManager, "custom")
    }

    override fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }
}