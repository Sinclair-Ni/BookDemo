package com.nzl.bookdemo.main.library

import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nzl.bookdemo.R
import com.nzl.bookdemo.adapter.BookAdapter
import com.nzl.bookdemo.adapter.BookVerticalAdapter
import com.nzl.bookdemo.base.BaseFragment
import com.nzl.bookdemo.bean.Book
import com.nzl.bookdemo.dao.BookManagedSQLiteOpenHelper

/**
 * FileName:   LibraryFragment
 * Author:     nizonglong
 * CreateTime: 2019/11/21 18:12
 */
class LibraryFragment : BaseFragment() {
    // adapters
    private lateinit var mBook1Adapter: BookVerticalAdapter
    private lateinit var mBook2Adapter: BookAdapter
    private lateinit var mBook3Adapter: BookVerticalAdapter
    private lateinit var mBook4Adapter: BookAdapter
    private lateinit var mBook5Adapter: BookVerticalAdapter
    private lateinit var mBook6Adapter: BookAdapter

    // book data
    private var books1 = ArrayList<Book>()
    private var books2 = ArrayList<Book>()
    private var books3 = ArrayList<Book>()
    private var books4 = ArrayList<Book>()
    private var books5 = ArrayList<Book>()
    private var books6 = ArrayList<Book>()


    private lateinit var db: BookManagedSQLiteOpenHelper

    private lateinit var rlType1: RelativeLayout
    private lateinit var rlType3: RelativeLayout
    private lateinit var rlType5: RelativeLayout

    private lateinit var rvType1: RecyclerView
    private lateinit var rvType2: RecyclerView
    private lateinit var rvType3: RecyclerView
    private lateinit var rvType4: RecyclerView
    private lateinit var rvType5: RecyclerView
    private lateinit var rvType6: RecyclerView

    override fun initLayout(): Int {
        return R.layout.fragment_library
    }

    override fun initView() {
        // DB
        db = BookManagedSQLiteOpenHelper.getInstance(this.context)

        // LayoutManager
        val lmHorizontal = LinearLayoutManager(this.context)
        lmHorizontal.orientation = LinearLayoutManager.HORIZONTAL

        val lmHorizontal2 = LinearLayoutManager(this.context)
        lmHorizontal2.orientation = LinearLayoutManager.HORIZONTAL

        val lmHorizontal3 = LinearLayoutManager(this.context)
        lmHorizontal3.orientation = LinearLayoutManager.HORIZONTAL

        val lmVertical = LinearLayoutManager(this.context)
        lmVertical.orientation = LinearLayoutManager.VERTICAL

        val lmVertical2 = LinearLayoutManager(this.context)
        lmVertical2.orientation = LinearLayoutManager.VERTICAL

        val lmVertical3 = LinearLayoutManager(this.context)
        lmVertical3.orientation = LinearLayoutManager.VERTICAL


        // set RelativeLayout
        rlType1 = getContentView()?.findViewById(R.id.rl_type1) as RelativeLayout
        rlType3 = getContentView()?.findViewById(R.id.rl_type3) as RelativeLayout
        rlType5 = getContentView()?.findViewById(R.id.rl_type5) as RelativeLayout

        // set RecyclerView
        rvType1 = getContentView()?.findViewById(R.id.rv_type1) as RecyclerView
        rvType1.layoutManager = lmHorizontal

        rvType2 = getContentView()?.findViewById(R.id.rv_type2) as RecyclerView
        rvType2.layoutManager = lmVertical

        rvType3 = getContentView()?.findViewById(R.id.rv_type3) as RecyclerView
        rvType3.layoutManager = lmHorizontal2

        rvType4 = getContentView()?.findViewById(R.id.rv_type4) as RecyclerView
        rvType4.layoutManager = lmVertical2

        rvType5 = getContentView()?.findViewById(R.id.rv_type5) as RecyclerView
        rvType5.layoutManager = lmHorizontal3

        rvType6 = getContentView()?.findViewById(R.id.rv_type6) as RecyclerView
        rvType6.layoutManager = lmVertical3
    }

    override fun initData() {
        // add data
        books1.addAll(db.queryTypeBook(1))
        books2.addAll(db.queryTypeBook(2))
        books3.addAll(db.queryTypeBook(3))
        books4.addAll(db.queryTypeBook(4))
        books5.addAll(db.queryTypeBook(5))
        books6.addAll(db.queryTypeBook(6))

        mBook1Adapter = BookVerticalAdapter(this.context, books1)
        mBook2Adapter = BookAdapter(this.context, books2)
        mBook3Adapter = BookVerticalAdapter(this.context, books3)
        mBook4Adapter = BookAdapter(this.context, books4)
        mBook5Adapter = BookVerticalAdapter(this.context, books5)
        mBook6Adapter = BookAdapter(this.context, books6)


        rvType1.adapter = mBook1Adapter
        rvType2.adapter = mBook2Adapter
        rvType3.adapter = mBook3Adapter
        rvType4.adapter = mBook4Adapter
        rvType5.adapter = mBook5Adapter
        rvType6.adapter = mBook6Adapter
    }

}