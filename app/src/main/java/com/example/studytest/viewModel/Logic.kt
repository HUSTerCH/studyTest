package com.example.studytest.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studytest.model.Data
import javax.sql.DataSource

class Logic(val data: Data) : ViewModel() {
    val userLiveData = data.getUserList()

    fun insertFlower() {

    }
}


class LogicFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(Logic::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return Logic(
                data = Data.getData(context.resources)
            ) as T
        }
    throw IllegalArgumentException("UNknown")
    }
}
