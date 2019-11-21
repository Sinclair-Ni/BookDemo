package com.nzl.bookdemo.base

import android.app.Activity
import android.util.Log


/**
 * FileName:   ActivityCollector
 * Author:     nizonglong
 * CreateTime: 2019/11/21 14:00
 */
object ActivityCollector {
    private val activities: MutableList<Activity> = ArrayList()
    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity?) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                Log.d("ActivityCollector", "finish activity:" + activity.javaClass.simpleName)
                activity.finish()
            }
        }
        activities.clear()
    }
}