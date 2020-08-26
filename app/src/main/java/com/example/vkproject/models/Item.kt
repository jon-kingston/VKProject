package com.example.vkproject.models

import io.realm.RealmObject

data class Item(
    val id: Int,
    val is_closed: Int,
    val name: String,
    val photo_100: String,
    val photo_200: String,
    val photo_50: String,
    val screen_name: String,
    val type: String
)