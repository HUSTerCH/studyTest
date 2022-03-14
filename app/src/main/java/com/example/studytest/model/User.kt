package com.example.studytest.model

import androidx.databinding.BaseObservable

data class User(var userName:String,var userAge:Int):BaseObservable() {
    fun convert() = userAge.toString()
}
