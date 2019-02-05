package com.example.fadeyin.mykt3.screens.notice


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.Gravity
import android.widget.Toast
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


class noticeActivity :  BaseActivity(0) {
    var noticeinfo : List<ResultNotice>? = null
    val animals: ArrayList<String> = ArrayList()

    var notice : String? = null
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
        SetupBottomNavigation()
            APIConfig.token = token
            val apiService2 = UserAPIinterface.createServiceWithAuth()
            apiService2.getNotice()
            .subscribeOn(Schedulers.io())
            .subscribe({
                    result ->
                noticeinfo = result
                getListNotice(result)
            }, {error ->
                Log.d("Result", "error")
                error.printStackTrace()
            }
            )

    }
    fun getListNotice(goNoticeInfo: List<ResultNotice>) {
        val rv = findViewById<RecyclerView>(R.id.rv_notice_list)
        rv.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val noticeList = ArrayList<ResultNotice>()
        noticeList.addAll(goNoticeInfo)
        var adapter = NoticeAdapter(noticeList)
        rv.adapter = adapter
    }
}
