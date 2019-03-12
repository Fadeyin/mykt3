package com.example.fadeyin.mykt3.screens.request

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.models.UserAPIinterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookingActivity : BaseActivity(1) {
    private var adapter: BookingAdapter? = null
    private var rv: RecyclerView? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        SetupBottomNavigation()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = ProgressBar.VISIBLE
// запускаем длительную операцию

        adapter = BookingAdapter()
        rv = findViewById(R.id.rv_booking_list)
        rv?.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv?.adapter = adapter
        val apiService2 = UserAPIinterface.createServiceWithAuth()
        apiService2.getBooking()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                Log.d("Result123", "$result ")
                progressBar.visibility = ProgressBar.INVISIBLE
                adapter?.setBookingList(result)
            }, { error ->
                apiService2.getBooking()
                Log.d("Result123", "error")
                error.printStackTrace()
            }
            )
        val fab: View = findViewById(R.id.fab2)
        fab.setOnClickListener { view ->
            val createComplaintIntent = Intent(this,CreateBooking::class.java)
            startActivity(createComplaintIntent)
        }
    }
}
