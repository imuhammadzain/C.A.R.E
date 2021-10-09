package com.example.care

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val screen = object : Thread(){
            override fun run() {
                Thread.sleep(1200)
                val intent=Intent(baseContext,Selection::class.java)
                startActivity(intent)
            }
        }
        screen.start()
    }

}