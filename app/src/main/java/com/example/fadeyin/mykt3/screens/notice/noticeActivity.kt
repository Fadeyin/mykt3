package com.example.fadeyin.mykt3.screens.notice


import android.annotation.SuppressLint
import android.app.AlertDialog
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
import android.content.DialogInterface
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.alert
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class noticeActivity :  BaseActivity(0) {

    var noticeinfo: List<ResultNotice>? = null
    val animals: ArrayList<String> = ArrayList()
    private var adapter: NoticeAdapter? = null
    private var rv: RecyclerView? = null
    var notice: String? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prfs = getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
        val token = prfs.getString("AccessToken", null)
        if (token == null) {
            val LoginIntent = Intent(this, LoginActivity::class.java)
            startActivity(LoginIntent)
        }
        setContentView(R.layout.activity_notice)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
        SetupBottomNavigation()
        APIConfig.token = token

        adapter = NoticeAdapter()
        rv = findViewById(R.id.rv_notice_list)
        rv?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv?.adapter = adapter

        val apiService2 = UserAPIinterface.createServiceWithAuth()
        apiService2.getNotice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                progressBar.visibility = ProgressBar.INVISIBLE
                adapter?.setNoticeList(result)
            }, { error ->
                apiService2.getNotice()
                Log.d("Result123", "error")
                error.printStackTrace()
            }
            )

        }
        override fun onBackPressed(){
            alert("Вы хотите выйти из приложения?", "Выход") {
                positiveButton("Да") {
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)}
                negativeButton("Нет") { }
            }.show()
    }
}
