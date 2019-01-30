package com.example.fadeyin.mykt3.screens.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.fadeyin.mykt3.BaseActivity
import com.example.fadeyin.mykt3.R
import com.example.fadeyin.mykt3.screens.LoginActivity

class MenuActivity :  BaseActivity(4) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        SetupBottomNavigation()
    }
    fun LogOut(view: View) {
        val preferences = getSharedPreferences("AUTHENTICATION", Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString("AccessToken", null)
        editor.apply()
        val LogOutIntent = Intent(this, LoginActivity::class.java)
        startActivity(LogOutIntent)
    }
}
