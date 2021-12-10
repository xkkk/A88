package com.baorun.handbook.a6v.widget

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SimpleFragmentStateAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {
    constructor(activity: FragmentActivity) : this(
        activity.supportFragmentManager,
        activity.lifecycle
    )

    constructor(fragment: Fragment) : this(fragment.childFragmentManager, fragment.lifecycle)

    private val fragments: MutableList<Fragment> = mutableListOf()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
    }

    fun addFragment(fragment: List<Fragment>) {
        fragments.addAll(fragment)
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}