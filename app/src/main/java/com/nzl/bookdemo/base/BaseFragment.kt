package com.nzl.bookdemo.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment


/**
 * FileName:   BaseFragment
 * Author:     nizonglong
 * CreateTime: 2019/11/21 14:01
 */
abstract class BaseFragment : Fragment() {
    private lateinit var mContentView: View
    private var toast: Toast? = null
    private val TAG = this.javaClass.simpleName
    /**
     * 调试开关,通activity中保持一致
     */
    private val DEBUG: Boolean = BaseActivity.DEBUG

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        mContentView = inflater.inflate(initLayout(), container, false)
        init()
        initView()
        initData()
        log("***********Fragment********$TAG")
        return mContentView
    }

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID * * @return 布局文件资源ID
     */
    protected abstract fun initLayout(): Int

    /**
     * 一些View的相关操作
     */
    protected abstract fun initView()

    /**
     * 一些Data的相关操作
     */
    protected abstract fun initData()

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据 * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    private fun init() {}

    fun getContentView(): View? {
        return mContentView
    }


    /**
     * 显示长toast
     *
     * @param msg 要显示的信息
     */
    fun toastLong(msg: String?) {
        if (null == toast) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
            toast?.show()
        } else {
            toast!!.setText(msg)
            toast!!.show()
        }
    }

    /**
     * 显示短toast
     *
     * @param msg 要显示的信息
     */
    fun toastShort(msg: String?) {
        if (null == toast) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
            toast?.show()
        } else {
            toast!!.setText(msg)
            toast!!.show()
        }
    }

    private fun log(msg: String?) {
        if (DEBUG) Log.d(TAG, msg)
    }

    /**
     * activity跳转
     *
     * @param cls 要跳转的活动
     */
    protected fun goNextActivity(cls: Class<*>?) {
        val intent = Intent(context, cls)
        startActivity(intent)
    }

    /**
     * activity跳转
     *
     * @param cls    要跳转的类
     * @param bundle 传递的数据
     */
    protected fun getNextActivity(cls: Class<*>?, bundle: Bundle?) {
        val intent = Intent(context, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }
}