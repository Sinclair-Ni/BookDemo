package com.nzl.bookdemo.login

import android.content.Intent
import android.widget.Toast
import com.nzl.bookdemo.MainActivity
import com.nzl.bookdemo.R
import com.nzl.bookdemo.base.BaseActivity
import com.nzl.bookdemo.daofun.UserService
import kotlinx.android.synthetic.main.activity_login.*

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
            //获取登录手机号
            val username = et_login_username.text.toString().trim()
            //获取密码输入框的值
            val password = et_login_password.text.toString().trim()

            //登录方法

            if (userService.login(username, password)) {
                val intent = Intent()
                //获取intent对象
                intent.setClass(this, MainActivity::class.java)
                // 获取class是使用::反射
                startActivity(intent)
            } else {
                Toast.makeText(this, "用户名或者密码错误", Toast.LENGTH_SHORT).show()
            }


        }
    }


}
