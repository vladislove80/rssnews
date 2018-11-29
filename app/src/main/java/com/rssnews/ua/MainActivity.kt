package com.rssnews.ua

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.rssnews.R
import com.rssnews.ua.base.BaseFragment
import com.rssnews.ua.general.GeneralNewsFragment
import com.rssnews.ua.regional.RegionalNewsFragment
import com.rssnews.ua.sport.SportNewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(GeneralNewsFragment.newInstance())
    }

    private fun addFragment(fragment: BaseFragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .replace(R.id.mainContainer, fragment, fragment.javaClass.canonicalName)
            .commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                addFragment(GeneralNewsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                addFragment(SportNewsFragment.newInstance())

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                addFragment(RegionalNewsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


}
