package com.example.vkproject.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vkproject.GroupElementFragment
import com.example.vkproject.modelsRealm.GroupsVkRealm

class ScreenSlidePagerRealmAdapter(
    fa: FragmentActivity,
    private val groups: GroupsVkRealm
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = groups.items.size

    override fun createFragment(position: Int): Fragment {
        return  GroupElementFragment(
            groups.items[position]!!.name,
            groups.items[position]!!.photo_200,
            groups.items[position]!!.screen_name)
    }

}