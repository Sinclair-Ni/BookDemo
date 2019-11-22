package com.nzl.bookdemo.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.nzl.bookdemo.bean.Book
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper
import java.math.BigDecimal

/**
 * FileName:   UserManagedSQLiteOpenHelper
 * Author:     nizonglong
 * CreateTime: 2019/11/21 16:18
 */
class BookManagedSQLiteOpenHelper(context: Context?, DB_VERSION: Int = CURRENT_VERSION) :
    ManagedSQLiteOpenHelper(context!!, DB_NAME, null, DB_VERSION) {
    companion object {
        private const val TAG = "BookManagedSQLiteOpenHelper"
        var DB_NAME = "bookstore.db" //数据库名称
        var CURRENT_VERSION = DbInfo.CURRENT_VERSION //当前的最新版本，如有表结构变更，该版本号要加一
        private var instance: BookManagedSQLiteOpenHelper? = null
        @Synchronized
        fun getInstance(ctx: Context?, version: Int = 0): BookManagedSQLiteOpenHelper {
            if (instance == null) {
                //如果调用时没传版本号，就使用默认的最新版本号
                instance =
                    if (version > 0) BookManagedSQLiteOpenHelper(ctx?.applicationContext, version)
                    else BookManagedSQLiteOpenHelper(ctx?.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableBookSql = "CREATE TABLE IF NOT EXISTS book (" +
                "bookid VARCHAR PRIMARY KEY  NOT NULL," +
                "book_name VARCHAR NOT NULL," +
                "book_desc VARCHAR NOT NULL," +
                "book_price REAL NOT NULL," +
                "book_main_pic VARCHAR," +
                "book_author VARCHAR);"
        Log.d(TAG, "create_book_sql:$createTableBookSql")
        db?.execSQL(createTableBookSql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            1 -> {
                /**
                 * 更新用户表新增 国家代码 字段
                 * 更新书籍表新增 章节 字段，默认0
                 */
                val bookSql = "alter table book add column book_chapter INTEGER NOT NULL default 0"
                Log.d("alter book", bookSql)
                db?.execSQL(bookSql)
                Log.d("onUpgrade Book", "升级数据库版本1->2 successful")
            }

        }
    }

    fun queryPageBook(pageIndex: Int, pageSize: Int): List<Book> {
        val sql = "select * from book limit ${pageIndex * pageSize},$pageSize;"
        Log.d("queryPageBook sql", sql)

        var bookArray = mutableListOf<Book>()
        use {
            val cursor = rawQuery(sql, null)
            if (cursor.moveToFirst()) {
                while (true) {
                    val book = Book("")
                    book.bookid = cursor.getString(0)
                    book.bookName = cursor.getString(1)
                    book.bookDesc = cursor.getString(2)
                    book.bookPrice = BigDecimal(cursor.getDouble(3))
                    book.bookMainPic = cursor.getString(4)
                    book.bookAuthor = cursor.getString(5)
//                    book.bookChapter = cursor.getInt(6)

                    bookArray.add(book)
                    if (cursor.isLast) {
                        break
                    }
                    cursor.moveToNext()
                }
            }
            cursor.close()
        }
        return bookArray
    }
}