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
        // book
        val createTableBookSql = "CREATE TABLE IF NOT EXISTS book (" +
                "bookid VARCHAR PRIMARY KEY  NOT NULL," +
                "book_name VARCHAR NOT NULL," +
                "book_desc VARCHAR NOT NULL," +
                "book_price REAL NOT NULL," +
                "book_main_pic VARCHAR," +
                "book_author VARCHAR,"+
                "book_chapter INTEGER default 0"+
        ");"
        Log.d(TAG, "create_book_sql:$createTableBookSql")
        db?.execSQL(createTableBookSql)

        // type
        val createTableTypeSql = "CREATE TABLE IF NOT EXISTS type (" +
                "typeid INTEGER PRIMARY KEY  NOT NULL," +
                "type_name VARCHAR NOT NULL);"
        Log.d(TAG, "createTableBookTypeSql:$createTableTypeSql")
        db?.execSQL(createTableTypeSql)

        // book_type
        val createTableBookTypeSql = "CREATE TABLE IF NOT EXISTS book_type (" +
                "bookid VARCHAR NOT NULL," +
                "typeid INTEGER NOT NULL," +
                "PRIMARY KEY ('bookid','typeid'));"
        Log.d(TAG, "createTableBookTypeSql:$createTableBookTypeSql")
        db?.execSQL(createTableBookTypeSql)

        db?.beginTransaction()
        // init data
        initData(db)
        db?.setTransactionSuccessful()
        db?.endTransaction()
    }

    private fun initData(db: SQLiteDatabase?){
        val insertBook = """
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b1', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b2', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b3', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b4', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b5', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b6', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b7', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b8', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b9', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b10', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b11', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b12', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b13', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b14', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b15', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b16', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b17', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b18', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b19', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b20', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b21', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b22', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b23', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b24', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b25', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b26', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b27', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b28', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b29', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b30', '择天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574224656883&di=54efefb512bac5537903ef447c86bc30&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftransform%2F20150925%2FZ3gQ-fxieymu0841712.jpg', '猫腻', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b31', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b32', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b33', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b34', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b35', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b36', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b37', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b38', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b39', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b40', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b41', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b42', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b43', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b44', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b45', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b46', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b47', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b48', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b49', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b50', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b51', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b52', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b53', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b54', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b55', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b56', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b57', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b58', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b59', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);
            INSERT INTO "book"("bookid", "book_name", "book_desc", "book_price", "book_main_pic", "book_author", "book_chapter") VALUES ('b60', '劈天记', '玄幻小说', 100.6, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574231428967&di=850c9d00932cc65b5616175998d9b368&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F53b5e89b98bd906d9ee1c7a790e14952de60be6a635c8-A2CMqL_fw658', '紫荆果', 0);

        """.trimIndent()
        val insertType = """
            INSERT INTO "type"("typeid", "type_name") VALUES (1, '玄幻');
            INSERT INTO "type"("typeid", "type_name") VALUES (2, '悬疑');
            INSERT INTO "type"("typeid", "type_name") VALUES (3, '爱情');
            INSERT INTO "type"("typeid", "type_name") VALUES (4, '穿越');
            INSERT INTO "type"("typeid", "type_name") VALUES (5, '童话');
            INSERT INTO "type"("typeid", "type_name") VALUES (6, '科学');

        """.trimIndent()
        val insertBookType = """
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b1', 1);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b1', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b2', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b3', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b4', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b5', 5);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b6', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b2', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b2', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b3', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b7', 1);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b8', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b9', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b10', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b11', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b12', 5);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b13', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b14', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b15', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b16', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b17', 1);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b18', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b19', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b20', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b21', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b22', 5);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b23', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b24', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b25', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b26', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b27', 1);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b28', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b29', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b30', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b31', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b32', 5);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b33', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b34', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b35', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b36', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b37', 1);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b38', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b39', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b40', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b41', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b42', 5);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b43', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b44', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b45', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b46', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b47', 1);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b48', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b49', 2);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b50', 3);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b51', 4);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b52', 5);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b53', 6);
            INSERT INTO "book_type"("bookid", "typeid") VALUES ('b54', 3);

        """.trimIndent()


        db?.execSQL(insertBook)
        db?.execSQL(insertType)
        db?.execSQL(insertBookType)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        when (oldVersion) {
            11 -> {
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
                    book.bookChapter = cursor.getInt(6)

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

    fun queryTypeBook(typeid: Int): List<Book> {
        val sql =
            "SELECT * from book where bookid in (SELECT bookid from book_type where typeid=$typeid);"
        Log.d("queryTypeBook sql", sql)

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
                    book.bookChapter = cursor.getInt(6)

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