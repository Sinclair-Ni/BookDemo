package com.nzl.bookdemo.bean

/**
 * FileName:   User
 * Author:     nizonglong
 * CreateTime: 2019/11/21 15:41
 */
data class User(
    var userid: String = "",
    var username: String = "",
    var password: String = "",
    var gender: String = "",
    var realName: String = "",
    var email: String = "",
    var phone: String = "",
    var headPic: String = "",
    var country_code: String = "86"
)