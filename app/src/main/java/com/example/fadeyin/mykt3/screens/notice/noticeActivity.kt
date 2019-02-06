package com.example.fadeyin.mykt3.screens.notice


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.APIConfig
import com.example.fadeyin.mykt3.models.AuthInfo
import com.example.fadeyin.mykt3.models.ResultNotice
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.LoginActivity
import io.reactivex.schedulers.Schedulers
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_notice.*
import android.widget.ProgressBar





class noticeActivity :  BaseActivity(0) {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prfs = getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
        val token = prfs.getString("AccessToken", null )
        if (token == null ) {
            val LoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(LoginIntent)
        }
        setContentView(R.layout.activity_notice)
        val rv = findViewById<RecyclerView>(R.id.rv_notice_list)
        val progressBar = findViewById(R.id.progressBar) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE
        SetupBottomNavigation()
            APIConfig.token = token
            val apiService2 = UserAPIinterface.createServiceWithAuth()
            apiService2.getNotice()
            .subscribeOn(Schedulers.io())
            .subscribe({
                    result ->
                Log.d("Result", "Success")
                getListNotice(result,rv,this)
                Log.d("Result", "Success 2")

            }, {error ->
                Log.d("Result", "error")
                error.printStackTrace()
            }
            )
        progressBar.visibility = ProgressBar.INVISIBLE
    }
}
