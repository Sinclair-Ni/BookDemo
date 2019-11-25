package com.nzl.bookdemo.daofun

import android.content.Context
import android.util.Log
import com.nzl.bookdemo.dao.MyManagedSQLiteOpenHelper

/**
 * FileName:   UserService
 * Author:     nizonglong
 * CreateTime: 2019/11/21 17:17
 */
class UserService(mContext: Context) {

    private val bookDb = MyManagedSQLiteOpenHelper.getInstance(mContext)

    fun login(username: String, password: String): Boolean {
        Log.d("UserService",username+password)

        if (username.isEmpty() || password.isEmpty()) {
            return false
        }

        val user = bookDb.queryLogin(username, password)
        if (user.userid.isNotEmpty()) {
            return true
        }

        return false
    }
}