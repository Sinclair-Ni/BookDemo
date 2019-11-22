package com.nzl.bookdemo.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.nzl.bookdemo.bean.User
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * FileName:   UserManagedSQLiteOpenHelper
 * Author:     nizonglong
 * CreateTime: 2019/11/21 16:18
 */
class UserManagedSQLiteOpenHelper(context: Context, DB_VERSION: Int = CURRENT_VERSION) :
    ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val TAG = "UserManagedSQLiteOpenHelper"
        var DB_NAME = "bookstore.db" //数据库名称
        var CURRENT_VERSION = DbInfo.CURRENT_VERSION //当前的最新版本，如有表结构变更，该版本号要加一
        private var instance: UserManagedSQLiteOpenHelper? = null
        @Synchronized
        fun getInstance(ctx: Context, version: Int = 0): UserManagedSQLiteOpenHelper {
            if (instance == null) {
                //如果调用时没传版本号，就使用默认的最新版本号
                instance =
                    if (version > 0) UserManagedSQLiteOpenHelper(ctx.applicationContext, version)
                    else UserManagedSQLiteOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTableUserSql = "CREATE TABLE user (" +
                "userid VARCHAR PRIMARY KEY NOT NULL," +
                "username VARCHAR NOT NULL," +
                "password VARCHAR NOT NULL," +
                "gender VARCHAR NOT NULL," +
                "real_name VARCHAR," +
                "email VARCHAR," +
                "phone VARCHAR," +
                "head_pic VARCHAR NOT NULL DEFAULT 'head_pic_url');"
        Log.d(TAG, "create_user_sql:$createTableUserSql")
        db?.execSQL(createTableUserSql)

        val init = """
            INSERT INTO "user"("userid", "username", "password", 
            "gender", "real_name", "email", "phone", "head_pic") 
            VALUES ('asd123njasdhq', 'nizonglong', '123456', '男', '倪宗龙', 
            'nizonglong@163.com', '17879552802', 'pic');
        """.trimIndent()
        db?.execSQL(init)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            1 -> {
                /**
                 * 更新用户表新增 国家代码 字段
                 * 更新书籍表新增 章节 字段，默认0
                 */
                val userSql =
                    "alter table user add column country_code VARCHAR NOT NULL default '86'"
                Log.d("alter user", userSql)
                db?.execSQL(userSql)

                Log.d("onUpgrade User", "升级数据库版本1->2 successful")
            }

        }
    }

    fun queryLogin(username: String, password: String): User {
        val sql = "select * from user where username='$username' and password='$password'"
        Log.d("query Login", sql)
        val info = User()
        use {
            val cursor = rawQuery(sql, null)
            if (cursor.moveToFirst()) {
                info.userid = cursor.getString(0)
                info.username = cursor.getString(1)
                info.password = cursor.getString(2)
                info.gender = cursor.getString(3)
                info.realName = cursor.getString(4)
                info.email = cursor.getString(5)
                info.phone = cursor.getString(6)
                info.headPic = cursor.getString(7)
                info.country_code = cursor.getString(8)
            }

            Log.d("queryed cursor", info.userid)
            cursor.close()
        }


        Log.d("queryed User", info.userid)
        return info
    }
}