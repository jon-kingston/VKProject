package com.example.vkproject.presenters

import android.util.Log
import com.example.secondfbtect.api.getAPI
import com.example.secondfbtect.presenters.Listener
import com.example.vkproject.models.GroupsVK
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PresenterImpl(var listener: Listener) : Presenter {

    override fun getGroupList(
        userId: String,
        count: String,
        extend: String,
        access_token: String,
        V: String
    ) {
        getAPI()
            .getGroupsById(userId, count, extend, access_token, V)
            .enqueue(object : Callback<GroupsVK> {
                override fun onFailure(call: Call<GroupsVK>, t: Throwable) {
                    Log.e("MY_TAG", "load FALL")
                    return listener.onListGroupFall()
                }

                override fun onResponse(call: Call<GroupsVK>, response: Response<GroupsVK>) {
                    if (response.isSuccessful) listener.onListGroupLoaded(response.body())
                    Log.e("MY_TAG", "success")
                }
            })
    }
}