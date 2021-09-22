package com.example.care

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Selection: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection)
}
    fun guest(v: View){
        val intent = Intent (this,Guest_Login::class.java)
        startActivity(intent)

    } fun Student(v:View){
        val intent = Intent (this,Login::class.java)
        startActivity(intent)

    }


}