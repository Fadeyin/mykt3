package com.example.fadeyin.mykt3.screens.request


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.APIConfig
import com.example.fadeyin.mykt3.models.UserAPIinterface
import com.example.fadeyin.mykt3.screens.LoginActivity
import com.example.fadeyin.mykt3.screens.notice.NoticeAdapter
import com.example.fadeyin.mykt3.screens.request.ComplaintAdapter
import io.reactivex.schedulers.Schedulers
import android.widget.ProgressBar
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.complaint_item.view.*


class ComplaintsActivity : BaseActivity(1) {
    private var adapter: ComplaintAdapter? = null
    private var rv: RecyclerView? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaints)
        SetupBottomNavigation()
        val progressBar = findViewById(R.id.progressBar) as ProgressBar
        progressBar.visibility = ProgressBar.VISIBLE
// запускаем длительную операцию

        adapter = ComplaintAdapter()
        rv = findViewById(R.id.rv_complaints_list)
        rv?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv?.adapter = adapter
        val apiService2 = UserAPIinterface.createServiceWithAuth()
        apiService2.getComplaints()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                progressBar.visibility = ProgressBar.INVISIBLE
                adapter?.setComplaintsList(result)
            }, { error ->
                apiService2.getNotice()
                Log.d("Result", "error")
                error.printStackTrace()
            }
            )
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            val createComplaintIntent = Intent(this,CreateComplaint::class.java)
            startActivity(createComplaintIntent)
        }
    }
}
