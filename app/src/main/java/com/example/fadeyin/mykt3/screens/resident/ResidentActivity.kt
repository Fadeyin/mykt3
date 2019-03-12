package com.example.fadeyin.mykt3.screens.resident


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.notice.NoticeAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_resident.view.*

class ResidentActivity :  BaseActivity(2) {
    private var adapter: ResidentAdapter? = null
    private var rv: RecyclerView? = null
    var Resident: String? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resident)
        SetupBottomNavigation()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
        adapter = ResidentAdapter()
        rv = findViewById(R.id.rv_residents_list)
        rv?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv?.adapter = adapter

        val apiService2 = UserAPIinterface.createServiceWithAuth()
        apiService2.getResidents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                progressBar.visibility = ProgressBar.INVISIBLE
                Log.d("Result123", "success:" + "$result")
                adapter?.setResidentList(result)
            }, { error ->
                apiService2.getResidents()
                Log.d("Result123", "error")
                error.printStackTrace()
            }
            )

    }
}
