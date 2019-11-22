package com.nzl.bookdemo

import androidx.viewpager.widget.ViewPager
import com.nzl.bookdemo.adapter.FragmentAdapter
import com.nzl.bookdemo.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {


    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter

    }

    override fun initData() {
        setNavigation()
    }

    /**
     * 设置底部导航栏
     */
    private fun setNavigation() {
        nav_view.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_library -> viewpager.currentItem = 0
                R.id.navigation_discovery -> viewpager.currentItem = 1
                R.id.navigation_me -> viewpager.currentItem = 2
            }
            true
        }

        // viewpager监听事件，当viewpager滑动时得到对应的fragment碎片
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                nav_view.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

}
