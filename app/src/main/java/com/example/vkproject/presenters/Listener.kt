package com.example.secondfbtect.presenters

import com.example.vkproject.models.GroupsVK

interface Listener {

    fun onListGroupLoaded(groupsVK: GroupsVK?)

    fun onListGroupFall()

}