package com.example.lion_nav_barhomepage.Appointment


import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.about.CustomDialogFragment
import com.example.lion_nav_barhomepage.databinding.FragmentAppointmentBinding
import com.example.lion_nav_barhomepage.doctors.ConfirmDialogFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsFragment
import com.example.lion_nav_barhomepage.doctors.DoctorsViewModel
import com.example.lion_nav_barhomepage.doctors.data
import java.text.SimpleDateFormat
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
    private val viewModel1: appointmentViewmodel by activityViewModels()

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
        var dayOfWeek: Int=0
        var selecteddate : String =""
        if (doc != null) {
            viewModel.setavl(doc.avl)
        }
        val avl : String = viewModel.text
        if( doc != null) {
            binding.showName.text = doc?.name.toString()
            binding.showAval.text = avl
        }
        val cal :Calendar=Calendar.getInstance()
        val simpleDateFormat  = SimpleDateFormat("dd-MM-yyyy")
        val currdate=simpleDateFormat.format(cal.time)
        val dd  = currdate.subSequence(0,2).toString().toInt()
        val mm  = currdate.subSequence(3,5).toString().toInt()
        val yyyy  = currdate.subSequence(6,10).toString().toInt()
//        Log.e("on","${dd},$mm,$yyyy}")

//        val cur_date : String = simpleDateFormat.format(calendar.getT)

        binding.calendar.setOnDateChangeListener(OnDateChangeListener { view, year, month, date ->
            val calendar: Calendar = Calendar.getInstance()
            calendar.set(year, month, date)
            dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            selecteddate="$date/${month+1}/$year"
            if (doc != null) {
                settimeslot(dayOfWeek, doc)
                check(date, month + 1, year, dd, mm, yyyy)
            }
        })
        var slot : String =""
        binding.selectDoctor.setOnClickListener {
            replaceFragment(DoctorsFragment())

        }
        binding.time1.setOnClickListener {
            slot="9:00 AM - 12:00 PM"
            if(doc!=null){
                settimeslot(dayOfWeek, doc)
                binding.time1.setBackgroundColor(Color.parseColor("#FF3C963F"))}

        }
        binding.time2.setOnClickListener {
            slot="6:00 PM - 9:00 PM"
            if(doc!=null){
                settimeslot(dayOfWeek, doc)
                binding.time2.setBackgroundColor(Color.parseColor("#FF3C963F"))}

        }
        binding.time3.setOnClickListener {
            slot="2:00 PM - 5:00 PM"
            if(doc!=null){
                settimeslot(dayOfWeek, doc)
                binding.time3.setBackgroundColor(Color.parseColor("#FF3C963F"))}

        }
        binding.confirm.setOnClickListener {
            val check = binding.avlSlots.isVisible
            Log.e("Check:","$check")

            if(doc==null || selecteddate=="" || slot==""){

                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("select appropriate data")
                    .setCancelable(true)
                val alert = dialogBuilder.create()
                alert.show()


            }
            else if (check==true) {
                val dialogBuilder = AlertDialog.Builder(requireActivity())
                dialogBuilder.setMessage("select appropriate data")
                    .setCancelable(true)
                val alert = dialogBuilder.create()
                alert.show()
            }

            else {
                viewModel1.set_data(doc.name,selecteddate,slot)
                var dialog = ConfirmDialogFragment()
                dialog.show(childFragmentManager,"custom")
            }
            Log.e("on","${viewModel1.get_date()}")


        }
        (activity as AppCompatActivity).supportActionBar?.title="Book Appointment"
        super.onViewCreated(view, savedInstanceState)
    }

    private fun check(date: Int, month: Int, year: Int, dd: Int, mm: Int, yyyy: Int) {
        if (date>dd && month>=mm && year>=yyyy){
            Log.e("on","1)True")


        }
        else if (date<dd && month>mm && year>=yyyy){
            Log.e("on","2)True")


        }
        else if (year>yyyy){
            Log.e("on","4)True")
        }
        else{
            binding.avlSlots.setText("Invalid Date")
            binding.avlSlots.isVisible=true

            binding.time1.isClickable=false
            binding.time1.setBackgroundColor(Color.parseColor("#FFE98383"))
            binding.time2.isClickable=false
            binding.time2.setBackgroundColor(Color.parseColor("#FFE98383"))
            binding.time3.isClickable=false
            binding.time3.setBackgroundColor(Color.parseColor("#FFE98383"))
            Log.e("on","3)False")

        }


    }
    fun invalid(){

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

                binding.avlSlots.setText("Slots not available")
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