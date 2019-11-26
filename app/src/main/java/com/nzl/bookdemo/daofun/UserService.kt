package com.nzl.bookdemo.daofun

import android.content.Context
import android.util.Log
import com.nzl.bookdemo.dao.MyManagedSQLiteOpenHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * FileName:   UserService
 * Author:     nizonglong
 * CreateTime: 2019/11/21 17:17
 */
class UserService(mContext: Context) {

    private val bookDb = MyManagedSQLiteOpenHelper.getInstance(mContext)

    fun login(username: String, password: String): Observable<Boolean> {
        Log.d("UserService", username + password)

        return Observable.create<Boolean> {
            Log.d("thread",Thread.currentThread().toString())

            if (bookDb.queryLogin(username, password).userid.isNotEmpty()) {
                it.onNext(true)
            } else {
                it.onNext(false)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }


}