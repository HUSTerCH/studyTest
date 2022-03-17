package com.example.studytest.model

import android.content.res.Resources
import com.example.studytest.R

fun userList(resources:Resources): List<User> {
    return listOf(
        User(
            userName = resources.getString(R.string.user_name1),
            userAge = 10,
            userId = 20210001
        ),
        User(
            userName = resources.getString(R.string.user_name2),
            userAge = 11,
            userId = 20210002
        ),
        User(
            userName = resources.getString(R.string.user_name3),
            userAge = 12,
            userId = 20210003
        ),
        User(
            userName = resources.getString(R.string.user_name4),
            userAge = 13,
            userId = 20210004
        ),
        User(
            userName = resources.getString(R.string.user_name5),
            userAge = 14,
            userId = 20210005
        ),
        User(
            userName = resources.getString(R.string.user_name6),
            userAge = 15,
            userId = 20210006
        ),
        User(
            userName = resources.getString(R.string.user_name7),
            userAge = 16,
            userId = 20210007
        ),
        User(
            userName = resources.getString(R.string.user_name8),
            userAge = 17,
            userId = 20210008
        ),
        User(
            userName = resources.getString(R.string.user_name10),
            userAge = 18,
            userId = 20210009
        ),
        User(
            userName = resources.getString(R.string.user_name1),
            userAge = 19,
            userId = 20210010
        ),
        User(
            userName = resources.getString(R.string.user_name2),
            userAge = 20,
            userId = 20210011
        ),
        User(
            userName = resources.getString(R.string.user_name10),
            userAge = 18,
            userId = 20210009
        ),
        User(
            userName = resources.getString(R.string.user_name1),
            userAge = 19,
            userId = 20210010
        ),
        User(
            userName = resources.getString(R.string.user_name2),
            userAge = 20,
            userId = 20210011
        ),

    )
}