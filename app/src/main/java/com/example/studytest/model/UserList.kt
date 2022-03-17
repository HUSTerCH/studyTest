package com.example.studytest.model

import android.content.res.Resources

fun userList(resources:Resources): List<User> {
    return listOf(
        User(
            userName = "小王",
            userAge = 10,
            userId = 20210001
        ),
        User(
            userName = "小李",
            userAge = 11,
            userId = 20210002
        ),
        User(
            userName = "小张",
            userAge = 12,
            userId = 20210003
        ),
        User(
            userName = "小赵",
            userAge = 13,
            userId = 20210004
        ),
        User(
            userName = "小孙",
            userAge = 14,
            userId = 20210005
        ),
        User(
            userName = "小周",
            userAge = 15,
            userId = 20210006
        ),
        User(
            userName = "小吴",
            userAge = 16,
            userId = 20210007
        ),
        User(
            userName = "小冯",
            userAge = 17,
            userId = 20210008
        )
    )
}