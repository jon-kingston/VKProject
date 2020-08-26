package com.example.vkproject.modelsRealm

import io.realm.RealmObject

open class ItemRealm(
    var name: String = "",
    var photo_200: String = "",
    var screen_name: String = ""
) : RealmObject()