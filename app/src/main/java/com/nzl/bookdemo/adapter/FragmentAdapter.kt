package com.nzl.bookdemo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nzl.bookdemo.base.BaseFragment
import com.nzl.bookdemo.main.discover.DiscoverFragment
import com.nzl.bookdemo.main.library.LibraryFragment
import com.nzl.bookdemo.main.me.MeFragment


/**
 * FileName:   FragmentAdapter
 * Author:     nizonglong
 * CreateTime: 2019/11/22 9:45
 */
class FragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    // 私有成员mFragments，加载页面碎片
    private val mFragments: MutableList<BaseFragment> = ArrayList()

    init {
        // 加载初始化Fragment
        mFragments.add(LibraryFragment())
        mFragments.add(DiscoverFragment())
        mFragments.add(MeFragment())
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> mFragments[0]
            1 -> {
                mFragments[1]
            }
            2 -> {
                mFragments[2]
            }
            3 -> {
                mFragments[3]
            }
            else -> {
                mFragments[0]
            }
        }
    }

    override fun getCount(): Int {
        return mFragments.size
    }


}