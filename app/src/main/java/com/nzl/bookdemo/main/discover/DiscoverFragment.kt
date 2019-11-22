package com.nzl.bookdemo.main.discover

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nzl.bookdemo.R
import com.nzl.bookdemo.adapter.BookAdapter
import com.nzl.bookdemo.base.BaseFragment
import com.nzl.bookdemo.bean.Book
import com.nzl.bookdemo.dao.BookManagedSQLiteOpenHelper
import kotlinx.android.synthetic.main.fragment_discover.*

/**
 * FileName:   DiscoveryFragment
 * Author:     nizonglong
 * CreateTime: 2019/11/21 18:02
 */
class DiscoverFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var pageIndex = 0

    private var books = ArrayList<Book>()

    private lateinit var mBookAdapter: BookAdapter

    private lateinit var db: BookManagedSQLiteOpenHelper

    override fun initLayout(): Int {
        return R.layout.fragment_discover
    }

    override fun initView() {
        db = BookManagedSQLiteOpenHelper.getInstance(this.context)
        books.addAll(db.queryPageBook(pageIndex, 16))

        mBookAdapter = BookAdapter(this.context, books)

        rv_book.adapter = mBookAdapter
        // val layoutManager = GridLayoutManager(this, 1)
        val layoutManager = LinearLayoutManager(this.requireContext())
        rv_book.layoutManager = layoutManager


    }

    override fun initData() {

        // 设置下拉监听
        srl_dynamic.setOnRefreshListener(this)
        // 刷新渐变颜色
        srl_dynamic.setColorSchemeResources(
            R.color.red,
            R.color.orange,
            R.color.lawnGreen,
            R.color.blue
        )

        rv_book.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int? = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem!! + 1 == mBookAdapter.itemCount) {
                    addData()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            }
        })

    }

    override fun onRefresh() {
        //关闭下拉刷新进度条
        srl_dynamic.isRefreshing = false
        addData()
    }

    private fun addData() {
        // 延迟2s刷新
        mHandler.postDelayed(mUpRefresh, 2000)
    }

    private val mHandler = Handler()

    private val mUpRefresh = Runnable {
        srl_dynamic.isRefreshing = false

        // page index + 1
        pageIndex += 1
        // book data refresh
        books.addAll(db.queryPageBook(pageIndex, 16))

        mBookAdapter.notifyDataSetChanged()
        //rv_book.scrollToPosition(pageIndex*16)
    }

    private val mRefresh = Runnable {
        srl_dynamic.isRefreshing = false

        // page index + 1
        pageIndex += 1
        // book data refresh
        val tempBook = books.clone()
        books.clear()
        books.addAll(db.queryPageBook(pageIndex, 16))
        books.addAll(tempBook as ArrayList<Book>)

        mBookAdapter.notifyDataSetChanged()
        rv_book.scrollToPosition(0)
    }
}