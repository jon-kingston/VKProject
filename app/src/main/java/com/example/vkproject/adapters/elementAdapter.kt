package com.example.pager_adapter.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.vkproject.GroupElementFragment
import com.example.vkproject.PageElementFragment
import com.example.vkproject.models.GroupsVK

class ScreenSlidePagerAdapter(
    fa: FragmentActivity,
    val groups: GroupsVK
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = groups.response!!.items!!.size

    override fun createFragment(position: Int): Fragment {
       return  GroupElementFragment(groups.response!!.items!![position].name,
           groups.response.items?.get(position)?.photo_200!!,
           groups.response.items[position].screen_name)
    }

}