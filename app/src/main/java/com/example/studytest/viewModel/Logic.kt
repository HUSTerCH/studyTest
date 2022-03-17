package com.example.studytest.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object Logic : ViewModel() {
    val i = MutableLiveData<Int>()
    var countReserved:Int = 0
    init {
        i.value = countReserved
    }

}