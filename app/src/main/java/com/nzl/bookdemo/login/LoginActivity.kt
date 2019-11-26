package com.nzl.bookdemo.login

import com.nzl.bookdemo.MainActivity
import com.nzl.bookdemo.R
import com.nzl.bookdemo.base.BaseActivity
import com.nzl.bookdemo.daofun.UserService
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {
    private lateinit var userService: UserService

    override fun initLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        userService = UserService(this)
    }

    override fun initData() {
        bt_login.setOnClickListener {
            //获取登录用户名
            val username = et_login_username.text.toString().trim()
            //获取密码输入框的值
            val password = et_login_password.text.toString().trim()

            //登录方法
            val observable = userService.login(username, password)

            val observer: Observer<Boolean> = object : Observer<Boolean> {
                override fun onComplete() {
                    println("All Completed")
                }

                override fun onNext(item: Boolean) {
                    println("Next $item")
                    if (item) {
                        goNextActivity(MainActivity::class.java)
                    } else {
                        toast("用户名或密码错误！")
                    }
                }

                override fun onError(e: Throwable) {
                    println("Error Occured ${e.message}")
                }

                override fun onSubscribe(d: Disposable) {
                    println("New Subscription ")
                }
            }

            observable.subscribe(observer)
        }
    }


}
