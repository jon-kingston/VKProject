package com.example.vkproject.modelsRealm

import io.realm.RealmList
import io.realm.RealmObject

open class GroupsVkRealm(
    var items : RealmList<ItemRealm> = RealmList(ItemRealm())
):RealmObject()