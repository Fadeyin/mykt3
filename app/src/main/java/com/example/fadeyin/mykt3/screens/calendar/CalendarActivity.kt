package com.example.fadeyin.mykt3.screens.calendar


import android.os.Bundle
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R


class CalendarActivity : BaseActivity(3) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        SetupBottomNavigation()
    }
}
