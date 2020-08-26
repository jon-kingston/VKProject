package com.example.vkproject.presenters

interface Presenter {

    fun getGroupList(userId: String, count: String, extend: String, access_token: String, V: String)

}