package com.example.fadeyin.mykt3

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.fadeyin.mykt3.screens.calendar.CalendarActivity
import com.example.fadeyin.mykt3.screens.menu.MenuActivity
import com.example.fadeyin.mykt3.screens.notice.noticeActivity
import com.example.fadeyin.mykt3.screens.request.RequestActivity
import com.example.fadeyin.mykt3.screens.resident.ResidentActivity
import kotlinx.android.synthetic.main.bottom_navigation_view.*

@Suppress("DEPRECATION")
abstract class BaseActivity(val navNumber: Int) : AppCompatActivity(){
    fun SetupBottomNavigation(){
        bottom_navigation_view.setTextVisibility(false)
        bottom_navigation_view.setIconSize(31f,31f)
        bottom_navigation_view.enableItemShiftingMode(false)
        bottom_navigation_view.enableShiftingMode(false)
        bottom_navigation_view.enableAnimation(false)
        for (i in 0 until bottom_navigation_view.menu.size()){
            bottom_navigation_view.setIconTintList(i,null)
        }
        bottom_navigation_view.setOnNavigationItemSelectedListener {
            val nextActivity =
                    when(it.itemId){
                        R.id.nav_item_events ->  noticeActivity::class.java
                        R.id.nav_item_request ->  RequestActivity::class.java
                        R.id.nav_item_residents ->  ResidentActivity::class.java
                        R.id.nav_item_support ->  CalendarActivity::class.java
                        R.id.nav_item_menu ->  MenuActivity::class.java
                        else -> {
                            null
                        }
                    }
            if (nextActivity !=null){
                val intent = Intent(this,nextActivity)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION


                startActivity(intent)
                overridePendingTransition(0,0)
                true
            }
            else
                false
        }
        bottom_navigation_view.menu.getItem(navNumber).isChecked = true

    }
}
