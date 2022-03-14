package com.example.studytest.viewModel

import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studytest.BR
import com.example.studytest.R
import com.example.studytest.model.User
import com.example.studytest.model.UserList

object Logic : ViewModel() {
    val i = MutableLiveData<Int>()
    var countReserved:Int = 0
    init {
        i.value = countReserved
    }

    fun getUserList() = UserList
    fun addUser() {
        for (i in 0..20)
            UserList.addUser(i, "item$i", 2 * i)
    }

    fun addNew() {
        UserList.addUser(i.value!!,"change",-2)
        i.value = countReserved++
    }

}