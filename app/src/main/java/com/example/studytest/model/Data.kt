package com.example.studytest.model

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class Data(resources: Resources) {
    private val initUserList = userList(resources)
    private val usersLivedata = MutableLiveData(initUserList)

    fun addUser(user: User) {
        val currentUserList = usersLivedata.value
        if (currentUserList != null) {
            val updatedList = currentUserList.toMutableList()
            updatedList.add(0,user)
            usersLivedata.postValue(updatedList)
        } else {
            usersLivedata.postValue(listOf(user))
        }
    }

    fun removeUser(user: User) {
        val currentList = usersLivedata.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(user)
            usersLivedata.postValue(updatedList)
        }
    }
    fun getUserList():LiveData<List<User>>{
        return usersLivedata
    }

    companion object {
        private var INSTANCE:Data? = null
        fun getData(resources: Resources):Data {
            return synchronized(Data::class) {
                val newInstance = INSTANCE ?:Data(resources)
                INSTANCE = newInstance
                newInstance

            }
        }
    }
}