package com.example.lion_nav_barhomepage.doctors

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DoctorsViewModel: ViewModel() {
    private val _doctors =  MutableLiveData<ArrayList<data>>()
    val doctors: LiveData<ArrayList<data>> = _doctors
    private val _pos =  MutableLiveData<Int>()
    val pos: LiveData<Int> = _pos
   // private val _avlwords =  MutableList<String>(7,index->"1"+index)
    val avlwords : MutableList<String> = arrayListOf()

    fun setposition(position: Int){
     _pos.value=position

     Log.e("on","${doctors.value?.get(position)}")
//     Log.e("print:::::","${get_data()}")
 }
    fun set_data(){
        _doctors.value=Helper.doc_list()
    }
    fun get_data(): data?
    {
        return _pos.value?.let { _doctors.value?.get(it )}
    }
    fun setavl(avl:List<Int>) {
        val weeks : MutableList<String>
        for (i in avl){
            when(i){
                1->avlwords.add("Sun")
                2->avlwords.add("Mon")
                3-> avlwords.add("Tue")
                4->avlwords.add("Wed")
                5->avlwords.add("Thur")
                6->avlwords.add("Fri")
                7->avlwords.add("Sat")


            }
            Log.e("on","$avlwords")
        }

        }
    }
