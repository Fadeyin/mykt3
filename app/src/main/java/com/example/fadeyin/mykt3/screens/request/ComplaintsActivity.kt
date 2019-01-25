package com.example.fadeyin.mykt3.screens.request

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R

class ComplaintsActivity : BaseActivity(1) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complaints)
        SetupBottomNavigation()
    }
}
