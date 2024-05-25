package com.example.lenscarporation.view

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.lenscarporation.R
import com.example.lenscarporation.utils.TabAdapter
import com.example.lenscarporation.utils.ViewPagerAdapter2
import com.example.lenscarporation.view.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewTabs: RecyclerView
    private lateinit var viewPager: ViewPager
    private val fragmentList = listOf(
        HomeFragment(),
        OrganizationFragment(),
        DITSFragment(),
        IRMSFragment(),
        EventFragment(),
        SenorityFragment(),
        CircularsFragment(),
        NewsFragment(),
        IRMSFragment(),
        LinksFragment()
    )
    private val tabNames = listOf(
        "Home", "Organization", "DITS/Panel", "IRMS", "Events",
        "Seniority", "Circulars", "News/Article", "IRPOBF", "Links"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBarColor()
        recyclerViewTabs = findViewById(R.id.recyclerViewTabs)
        viewPager = findViewById(R.id.viewPagerS)

        recyclerViewTabs.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTabs.adapter = TabAdapter(tabNames) { position ->
            viewPager.currentItem = position
        }

        viewPager.adapter = ViewPagerAdapter2(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ).apply {
            fragmentList.forEachIndexed { index, fragment ->
                addFragment(fragment, tabNames[index])
            }
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                Log.d("Debugging", "Current fragment: ${fragmentList[position].javaClass.simpleName}")
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

    }
    private fun setStatusBarColor() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getResources().getColor(R.color.black)
    }

}
