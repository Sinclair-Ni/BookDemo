package com.nzl.bookdemo.main.discovery

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nzl.bookdemo.R
import com.nzl.bookdemo.base.BaseFragment

/**
 * FileName:   DiscoveryFragment
 * Author:     nizonglong
 * CreateTime: 2019/11/21 18:02
 */
class DiscoveryFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {


    override fun initLayout(): Int {
        return R.layout.fragment_discovery
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun onRefresh() {

    }
}