package com.nzl.bookdemo

import com.nzl.bookdemo.base.BaseActivity
import com.nzl.bookdemo.main.discovery.DiscoveryFragment
import com.nzl.bookdemo.main.library.LibraryFragment
import com.nzl.bookdemo.main.me.MeFragment

class MainActivity : BaseActivity() {
    private lateinit var libraryFragment: LibraryFragment
    private lateinit var discoveryFragment: DiscoveryFragment
    private lateinit var meFragment: MeFragment


    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        libraryFragment = LibraryFragment()

    }

    override fun initData() {
    }

}
