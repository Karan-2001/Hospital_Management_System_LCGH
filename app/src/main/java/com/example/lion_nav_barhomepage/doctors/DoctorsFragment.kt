package com.example.lion_nav_barhomepage.doctors

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lion_nav_barhomepage.R
import com.example.lion_nav_barhomepage.databinding.FragmentDoctorsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [DoctorsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoctorsFragment : Fragment() ,DoctorsAdapter.Click ,DoctorsAdapter.replace{
    private var _binding: FragmentDoctorsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var search: EditText
    lateinit var myview: View
    private val viewModel: DoctorsViewModel by activityViewModels()
    override fun go_to_main(R_id: Int) {
    viewModel.setposition(R_id)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//      arguments?.let {
//          param1 = it.getString(ARG_PARAM1)
//
//      }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDoctorsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.RecyclerView
        search = binding.sampleEditText
        recyclerView.adapter = DoctorsAdapter(this, Helper.doc_list(), this,this)
        myview = view
        viewModel.set_data()
        (activity as AppCompatActivity).supportActionBar?.title="Doctors"




        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })


    }

    fun filter(text: String) {
        val filtered: ArrayList<data> = ArrayList()

        val courseAry: ArrayList<data> = Helper.doc_list()

        for (eachCourse in courseAry) {
            if (eachCourse.name!!.toLowerCase()
                    .contains(text.toLowerCase()) || eachCourse.spec!!.toLowerCase()
                    .contains(text.toLowerCase())
            ) {
                filtered.add(eachCourse)
            }
        }

        //calling a method of the adapter class and passing the filtered list
//        DoctorsAdapter(this,filtered)
        recyclerView.adapter = DoctorsAdapter(this, filtered, this,this)
    }

    override fun replaceFragment(fragment: Fragment) {
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout, fragment)
        fragmentTransaction.commit()

    }


}


