package com.example.fadeyin.mykt3.screens.request


import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.screens.notice.noticeActivity

class RequestActivity :  BaseActivity(1) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)
        SetupBottomNavigation()
    }

    fun goComplaints(view: View){
        val complaintsIntent = Intent(this, ComplaintsActivity::class.java)
        startActivity(complaintsIntent)
    }
    fun goBooking(view: View){
        val bookingIntent = Intent(this,BookingActivity::class.java)
        startActivity(bookingIntent)
    }
}
