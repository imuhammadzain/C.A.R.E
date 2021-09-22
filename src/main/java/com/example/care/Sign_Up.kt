package com.example.care

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup_activity.*

class Sign_Up : AppCompatActivity() {
    private var S_Auth : FirebaseAuth? = null
    private var S_Prg : ProgressDialog?=null
    private lateinit var S_Fname : String
    private lateinit var S_Lname : String
    private lateinit var S_Email : String
    private lateinit var S_Phn : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_activity)
        S_Auth= FirebaseAuth.getInstance()
        S_Prg= ProgressDialog(this)
        stdsignup.setOnClickListener {
            Register()
        }



    }
    fun Register(){
        var e:String = email.text.toString()
        var p:String = Password.text.toString()
        S_Prg?.setMessage("Signing Up!")
        S_Prg?.show()
        if (e.isEmpty()||p.isEmpty()){
            Toast.makeText(this,"fill all feilds", Toast.LENGTH_SHORT).show()
        }else{
            S_Auth!!.createUserWithEmailAndPassword(e,p).addOnCompleteListener{ task->
                if (task.isSuccessful ){
                    S_Prg?.dismiss()
                    Toast.makeText(this,"Registered!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,Login::class.java))
                }else{
                    S_Prg?.dismiss()
                    Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show()
                }

            }
        }




    }

    fun signup(v: View){
        val intent = Intent (this,Login::class.java)
        startActivity(intent)

    }
}