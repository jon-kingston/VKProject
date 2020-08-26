package com.example.vkproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_element.*
import kotlinx.android.synthetic.main.group_element.*

class PageElementFragment(var name: String) : Fragment(R.layout.fragment_element) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView2.text = name
    }

}

class GroupElementFragment(var name: String, private val photo: String, private val skrinName: String) : Fragment(R.layout.group_element) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = name

        Picasso.get()
            .load(photo)
            .into(imageView2)

        textView.setOnClickListener() {
            Log.e("onClick", name)
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/$skrinName"))
            startActivity(i)
        }

    }

}