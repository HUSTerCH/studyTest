package com.example.studytest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.studytest.R
import com.example.studytest.databinding.ActivityEditBinding
import com.example.studytest.viewModel.Logic

class EditActivity:AppCompatActivity() {
    lateinit var editBinding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editBinding = DataBindingUtil.setContentView<ActivityEditBinding>(this, R.layout.activity_edit)
        editBinding.logic = Logic
    }
}