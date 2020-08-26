package com.example.secondfbtect.api

import com.example.vkproject.models.GroupsVK
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("groups.get")
    fun getGroupsById(
        @Query("user_id") userId: String,
        @Query("count") count: String,
        @Query("extended") exten: String,
        @Query("access_token") access_token: String,
        @Query("v") V: String
        ): Call<GroupsVK>

//    @GET("users/{id}")
//    fun getUserById(@Path("id") id: String) : Call<User>
}