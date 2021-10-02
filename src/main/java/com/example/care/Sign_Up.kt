package com.example.care

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.care.model.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup_activity.*

class Sign_Up : AppCompatActivity() {
    private var S_Auth: FirebaseAuth? = null
    private var S_Prg: ProgressDialog? = null
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var stdEmail: String
    lateinit var phone: String
    lateinit var p: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)



        init()
    }

    private fun init() {
        S_Auth = FirebaseAuth.getInstance()
        S_Prg = ProgressDialog(this)
        stdsignup.setOnClickListener {
            Register()
        }
    }

    fun Register() {
        p = Password.text.toString()
        firstName = first_name.text.toString()
        lastName = last_name.text.toString()
        stdEmail = email.text.toString()
        phone = Phn_no.text.toString()

        S_Prg?.setMessage("Signing Up!")
        S_Prg?.show()
        if (stdEmail.isEmpty() || p.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "fill all feilds", Toast.LENGTH_SHORT).show()
        } else {
            S_Auth!!.createUserWithEmailAndPassword(stdEmail, p).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    S_Prg?.dismiss()
                    saveToDatabase()
                    Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, Login::class.java))
                } else {
                    S_Prg?.dismiss()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun saveToDatabase() {
        val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myreference: DatabaseReference =
            firebaseDatabase.getReference().child("UserInfo").child(S_Auth?.uid.toString())
        val stddata = UserInfo(firstName, lastName, stdEmail, phone)

        myreference.setValue(stddata)
    }
}