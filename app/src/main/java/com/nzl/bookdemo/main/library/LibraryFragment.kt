package com.nzl.bookdemo.main.library

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nzl.bookdemo.R
import com.nzl.bookdemo.adapter.BookAdapter
import com.nzl.bookdemo.base.BaseFragment
import com.nzl.bookdemo.bean.Book
import com.nzl.bookdemo.dao.MyManagedSQLiteOpenHelper
import kotlinx.android.synthetic.main.fragment_library.*

/**
 * FileName:   LibraryFragment
 * Author:     nizonglong
 * CreateTime: 2019/11/21 18:12
 */
class LibraryFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener  {

    private var pageIndex = 0
    private var books = ArrayList<Book>()
    private lateinit var mBookAdapter: BookAdapter
    private lateinit var db: MyManagedSQLiteOpenHelper
    private lateinit var rvBook: RecyclerView
    private lateinit var srlDynamic: SwipeRefreshLayout

    override fun initLayout(): Int {
        return R.layout.fragment_library
    }

    override fun initView() {
        db = MyManagedSQLiteOpenHelper.getInstance(this.context)
        books.addAll(db.queryPageBook(pageIndex, 16))
        mBookAdapter = BookAdapter(this.context, books)

        rvBook = getContentView()?.findViewById(R.id.rv_book) as RecyclerView
        srlDynamic = getContentView()?.findViewById(R.id.srl_dynamic) as SwipeRefreshLayout
    }


    override fun initData() {
        // val layoutManager = GridLayoutManager(this, 1)
        val layoutManager = LinearLayoutManager(this.context)
        rvBook.layoutManager = layoutManager

        rvBook.adapter = mBookAdapter

        // 设置下拉监听
        srlDynamic.setOnRefreshListener(this)
        // 刷新渐变颜色
        srlDynamic.setColorSchemeResources(
            R.color.red,
            R.color.orange,
            R.color.lawnGreen,
            R.color.blue
        )

        rvBook.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        srlDynamic.isRefreshing = false
        addData()
    }

    private fun addData() {
        // 延迟2s刷新
        mHandler.postDelayed(mUpRefresh, 2000)
    }

    private val mHandler = Handler()

    private val mUpRefresh = Runnable {
        srlDynamic.isRefreshing = false

        // page index + 1
        pageIndex += 1
        // book data refresh
        books.addAll(db.queryPageBook(pageIndex, 16))

        mBookAdapter.notifyDataSetChanged()
        //rv_book.scrollToPosition(pageIndex*16)
    }

    private val mRefresh = Runnable {
        srlDynamic.isRefreshing = false

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