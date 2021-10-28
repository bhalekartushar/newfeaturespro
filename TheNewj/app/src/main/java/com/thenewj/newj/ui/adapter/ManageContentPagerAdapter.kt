package com.thenewj.newj.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ManageContentPagerAdapter(fm:FragmentManager):FragmentStatePagerAdapter(fm) {

    val fragmentList: MutableList<Fragment> = ArrayList<Fragment>()
    val fragmentTitlelIst: MutableList<String> = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitlelIst.get(position)
    }

    fun addFragment(fragment: Fragment, title:String){
        fragmentList.add(fragment)
        fragmentTitlelIst.add(title)
    }
}