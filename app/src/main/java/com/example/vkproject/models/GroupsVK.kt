package com.example.vkproject.models

import io.realm.RealmObject
import kotlin.Error

data class GroupsVK(
    val response: Response?,

    val error: com.example.vkproject.models.Error?
)