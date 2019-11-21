package com.nzl.bookdemo.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity


/**
 * Author: nizonglong
 * C_Time: 2019/11/21 13:53
 */
abstract class BaseActivity : AppCompatActivity() {
    /***是否显示标题栏 */
    private var isShowTitle = false
    /***是否显示状态栏 */
    private var isShowStatus = true
    /***封装toast对象，避免toast一直显示 */
    private var toast: Toast? = null
    /***获取TAG的activity名称 */
    private val TAG = this.javaClass.simpleName

    /**
     * .全局调试开关，fragment中同样使用这个。
     */
    companion object {
        val DEBUG = true
    }


    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isShowTitle) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            supportActionBar?.hide()
        }
        if (!isShowStatus) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        //设置布局
        setContentView(initLayout())
        //初始化控件
        initView()
        //设置数据
        initData()
        log("*********Activity********$TAG")
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    /**
     * 设置布局
     *
     * @return 该活动的xml文件
     */
    abstract fun initLayout(): Int

    /**
     * 初始化布局
     */
    abstract fun initView()

    /**
     * 设置数据
     */
    abstract fun initData()

    /**
     * 设置是否标题栏
     *
     * @param isShow true-->显示，false-->不显示
     */
    fun setTitle(isShow: Boolean) {
        isShowTitle = isShow
    }

    /**
     * 设置是否显示状态栏
     *
     * @param isShow true-->显示，false-->不显示
     */
    fun setState(isShow: Boolean) {
        isShowStatus = isShow
    }

    /**
     * 显示长toast
     *
     * @param msg 要显示的信息
     */
    fun toastLong(msg: String?) {
        if (null == toast) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
            toast!!.show()
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
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
            toast!!.show()
        } else {
            toast!!.setText(msg)
            toast!!.show()
        }
    }

    fun log(msg: String?) {
        if (DEBUG) Log.d(TAG, msg)
    }


    /**
     * activity跳转
     *
     * @param cls 要跳转的活动
     */
    fun goNextActivity(cls: Class<*>?) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    /**
     * activity跳转
     *
     * @param cls 要跳转的类
     * @param bundle 传递的数据
     */
    fun getNextActivity(cls: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, cls)
        intent.putExtras(bundle!!)
        startActivity(intent)
    }
}