package com.example.lion_nav_barhomepage.doctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DoctorsViewModel: ViewModel() {
    private val _doctors =  MutableLiveData<List<data>>()
    val doctors: LiveData<List<data>> = _doctors

}