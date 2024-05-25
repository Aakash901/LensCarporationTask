package com.example.lenscarporation.utils

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class ViewPagerAdapter2(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior) {
    private val fragments: MutableList<Fragment> = ArrayList()
    private val fragmentTitle: MutableList<String> = ArrayList()

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        fragmentTitle.add(title)
        Log.d("Debugging", "Fragment added: ${fragment.javaClass.simpleName}")
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }


    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }


    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }
}