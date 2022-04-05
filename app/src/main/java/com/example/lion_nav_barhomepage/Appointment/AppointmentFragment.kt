package com.example.lion_nav_barhomepage.Appointment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentAppointmentBinding
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsViewModel
import com.example.lion_nav_barhomepage.doctors.data
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AppointmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AppointmentFragment : Fragment() {

    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DoctorsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val doc : data? = viewModel.get_data()
        if (doc != null) {
            viewModel.setavl(doc.avl)
        }
        val avl : MutableList<String> = viewModel.avlwords
        binding.showName.text=doc?.name.toString()
        binding.showAval.text= avl.toString()
        binding.calendar.setOnDateChangeListener(OnDateChangeListener { view, year, month, date ->
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(year, month, date)
            val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
//            Log.e("on","array:$date/$month/$year-$dayOfWeek")
            settimeslot(dayOfWeek,doc)
        })
        //val date = binding.calendar

    binding.selectDoctor.setOnClickListener {
        replaceFragment(DoctorsFragment())

    }
        (activity as AppCompatActivity).supportActionBar?.title="Book Appointment"
        super.onViewCreated(view, savedInstanceState)
    }

    private fun settimeslot(dayOfWeek: Int, doc: data?) {
        val week = doc?.avl

        if (week!=null) {
            val slots = doc.timeslots[dayOfWeek - 1]
            if (week.indexOf(dayOfWeek) != -1) {
                binding.avlSlots.isVisible=false
                if (slots[0] == 1){
                    binding.time1.isClickable=true
                    binding.time1.setBackgroundColor(Color.parseColor("#FFE44545"))
                }
                else{
                    binding.time1.isClickable=false
                    binding.time1.setBackgroundColor(Color.parseColor("#FFE98383"))
                }
                if (slots[1] == 1){
                    binding.time3.isClickable=true
                    binding.time3.setBackgroundColor(Color.parseColor("#FFE44545"))
                }
                else{
                    binding.time3.isClickable=false
                    binding.time3.setBackgroundColor(Color.parseColor("#FFE98383"))

                }
                if (slots[2] == 1){
                    binding.time2.isClickable=true
                    binding.time2.setBackgroundColor(Color.parseColor("#FFE44545"))
                }
                else{
                    binding.time2.isClickable=false
                    binding.time2.setBackgroundColor(Color.parseColor("#FFE98383"))
                }
            }
//            FFE44545
            else{
                binding.avlSlots.isVisible=true
                binding.time1.isClickable=false
                binding.time2.isClickable=false
                binding.time3.isClickable=false
                binding.time2.setBackgroundColor(Color.parseColor("#FFE98383"))
                binding.time1.setBackgroundColor(Color.parseColor("#FFE98383"))
                binding.time3.setBackgroundColor(Color.parseColor("#FFE98383"))

                Log.e("on","false")
            }
        }
        else{
            binding.time2.setBackgroundColor(Color.parseColor("#FFE98383"))
            binding.time1.setBackgroundColor(Color.parseColor("#FFE98383"))
            binding.time3.setBackgroundColor(Color.parseColor("#FFE98383"))
            Log.e("on","false")
        }

    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }

}