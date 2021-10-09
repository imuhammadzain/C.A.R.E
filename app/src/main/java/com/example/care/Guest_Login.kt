package com.example.care

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Guest_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guest_login_activity)

    }
    fun GLog_in(v:View){
        val intent = Intent (this,HOME::class.java)
        startActivity(intent)

    }

}