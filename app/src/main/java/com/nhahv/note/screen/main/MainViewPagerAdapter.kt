package com.nhahv.note.screen.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
class MainViewPagerAdapter(supportManager: FragmentManager,
                           fragments: ArrayList<Fragment>) : FragmentPagerAdapter(supportManager) {
    private val mFragments = fragments

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }
}