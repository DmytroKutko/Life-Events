package com.life.events.ui.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.life.events.R
import com.life.events.utils.JsonConverter
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment(R.layout.fragment_user) {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString("id")!!
        initUserData(id)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initUserData(id: String) {
        val user = JsonConverter.getById(id)
        Glide.with(this)
            .load(user?.picture)
            .centerCrop()
            .apply(RequestOptions())
            .placeholder(R.mipmap.ic_user_icon)
            .error(R.mipmap.ic_user_icon)
            .into(ivUserImage)
        tvName.text = user?.name
        tvAbout.text = "About:\n${user?.about}"
    }
}