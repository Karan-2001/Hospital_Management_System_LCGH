package com.example.lion_nav_barhomepage.Home

import `in`.codeshuffle.typewriterview.TypeWriterListener
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
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
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.abs

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
    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList:ArrayList<Int>
    private lateinit var adapter: ImageAdapter





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
//            imageSlider=binding.imageSlider
//            val imagelist = ArrayList<SlideModel>()
//
//
//            imagelist.add(SlideModel(R.drawable.one))
//            imagelist.add(SlideModel(R.drawable.img1))
//            imagelist.add(SlideModel(R.drawable.img5))
//            imageSlider.setImageList(imagelist,ScaleTypes.FIT)
            val view = binding.root
            return view
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        setUpTransformer()

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        })
        (activity as AppCompatActivity).supportActionBar?.title="Home"
        if( patient_main_data.name != null){
            binding.pName.setDelay(300)
            binding.pName.animateText("Hello "+ patient_main_data.name.toString()+"!")

        }
        else{
            binding.pName.setDelay(300)
            binding.pName.animateText("Hello!!")
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
//        getInfo()

        val collapsingToolbarLayout: CollapsingToolbarLayout = binding.collapsingToolbar
        val animationDrawable = collapsingToolbarLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.setEnterFadeDuration(2500)
        animationDrawable.start()


    }
    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 4000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.65f + r * 0.16f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = binding.imageSlider
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.one)
        imageList.add(R.drawable.img1)
        imageList.add(R.drawable.img2)
        imageList.add(R.drawable.imge)
        imageList.add(R.drawable.img4)
        imageList.add(R.drawable.img5)
//        imageList.add(R.drawable.img6)
//        imageList.add(R.drawable.eight)


        adapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()
    }
//    private fun getInfo(){
//     val url= "https://api.rootnet.in/covid19-in/stats/latest"
//        val obj = JSONObject(url)
//        val queue = Volley.newRequestQueue(context)
//        val request = JsonObjectRequest(Request.Method.GET.toString(),obj,null) { response ->
//            try {
//
//                val dataObj: JSONObject = obj.getJSONObject("data")
//                val summaryObj: JSONObject = obj.getJSONObject("summary")
//                val cases:Int = summaryObj.getInt("total")
//                val recovered:Int = summaryObj.getInt("discharged")
//                val deaths:Int = summaryObj.getInt("deaths")
//                binding.acases.text=cases.toString()
//                binding.rcases.text=recovered.toString()
//                binding.dcases.text=deaths.toString()
//
//            } catch (e: JSONException) {
//                e.printStackTrace()
//            }
//        }
//        queue.add(request)
//
//    }




    }

