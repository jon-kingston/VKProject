package com.example.vkproject.aktivitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.secondfbtect.presenters.Listener
import com.example.vkproject.presenters.PresenterImpl
import com.example.vkproject.R
import com.example.vkproject.adapters.ScreenSlidePagerRealmAdapter
import com.example.vkproject.makeToast
import com.example.vkproject.models.GroupsVK
import com.example.vkproject.modelsRealm.GroupsVkRealm
import com.example.vkproject.modelsRealm.ItemRealm
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.auth.VKScope
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), Listener, SwipeRefreshLayout.OnRefreshListener {

    val SAVE_USER_ID = "userId"
    val SAVE_TOKEN = "token"

    var userIdd = "userId"
    var toc = "token"

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefresh.setOnRefreshListener(this)
        swipeRefresh.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )

        getPreferences(Context.MODE_PRIVATE).apply {
            userIdd = getString(SAVE_USER_ID, "userId")!!
            toc = getString(SAVE_TOKEN, "token")!!
        }

        Realm.init(this)
        realm = Realm.getDefaultInstance()
        val realmGroup = realm.where<GroupsVkRealm>().findFirst()

        if (realmGroup == null) {
            Log.e("reALM", "nULL")
        }
        else {
            loadingFromBD(realmGroup)
            Log.e("reALM", "no nULL")
        }

//        Karman
//        realm.executeTransaction {
////            it.copyToRealm<ManRealm>(listOf(Man("lola").toRealmVersion()))
//            it.where<ManRealm>().findAll()
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                userIdd = token.userId.toString()
                toc = token.accessToken
                getPreferences(Context.MODE_PRIVATE)
                    .edit {
                        putString(SAVE_TOKEN, toc)
                        putString(SAVE_USER_ID, userIdd)
                    }

                Log.e("token", token.accessToken)
                Log.e("userID", token.userId.toString())

                onRefresh()
            }

            // User didn't pass authorization
            override fun onLoginFailed(errorCode: Int) =
                makeToast("Foll!!!")
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback))
            super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onListGroupLoaded(groupsVK: GroupsVK?) {
        if (groupsVK != null) {
            if (groupsVK.error != null) {
                makeToast(groupsVK.error.error_code.toString())
                VK.login(this, arrayListOf(VKScope.WALL, VKScope.PHOTOS))
            } else {
                Log.e("group", "No Null")

                if (groupsVK.response?.items?.size != null) {
                    Log.e("gp", groupsVK.response.items.size.toString())
                    //makeToast("success, чувак все загруженно!!!!")
                    loadIngInBD(groupsVK)
                } else Log.e("gp", "is null")
            }
        }
    }

    override fun onListGroupFall() {
        makeToast("Fall, загрузка не совершена!!!!")
    }

    override fun onRefresh() {
        val runnable = Runnable {
            PresenterImpl(this@MainActivity)
                .getGroupList(userIdd, "100", "1", toc, "5.110"
                )
            swipeRefresh.isRefreshing = false
        }
        Handler().postDelayed(runnable, 1000)
    }

    private fun loadingFromBD(groupsRealm : GroupsVkRealm){
        vp2.adapter = ScreenSlidePagerRealmAdapter(this, groupsRealm)
        makeToast("Загруженно из БД")
    }

    private fun loadIngInBD(groupsVK: GroupsVK?) {

            realm.executeTransaction { realm ->
                // Add a person
                val groupsRealm = realm.createObject<GroupsVkRealm>()

                groupsVK!!.response!!.items!!.forEachIndexed { index, item ->
                    groupsRealm.items.add(index,
                        ItemRealm(item.name, item.photo_200, item.screen_name))
                }

                loadingFromBD(groupsRealm)
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}


