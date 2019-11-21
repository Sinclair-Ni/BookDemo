package com.nzl.bookdemo.base

import android.app.Application
import com.nzl.bookdemo.bean.User


/**
 * FileName:   MyApp
 * Author:     nizonglong
 * CreateTime: 2019/11/21 14:14
 */
object MyApp : Application() {
    private var currentUser: User? = null
    fun getCurrentUser(): User? {
        return currentUser
    }

    fun setCurrentUser(user: User?) {
        currentUser = user
    }
}
