package com.rssnews.ua

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rssnews.R
import com.rssnews.data.model.Categories
import com.rssnews.ua.fragment.base.BaseFragment
import com.rssnews.ua.fragment.NewsFragment
import com.rssnews.util.general
import com.rssnews.util.regional
import com.rssnews.util.sport
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(NewsFragment.newInstance(Categories(general)))
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        val title = navigation.menu.getItem(0).title
        supportActionBar?.title = title
    }

    private fun addFragment(fragment: BaseFragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.mainContainer, fragment, fragment.javaClass.simpleName).commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                toolbar.title = navigation.menu.findItem(R.id.navigation_home).toString()
                addFragment(NewsFragment.newInstance(Categories(general)))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                toolbar.title = navigation.menu.findItem(R.id.navigation_dashboard).toString()

                addFragment(NewsFragment.newInstance(Categories(sport)))
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                toolbar.title = navigation.menu.findItem(R.id.navigation_notifications).toString()
                addFragment(NewsFragment.newInstance(Categories(regional)))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
