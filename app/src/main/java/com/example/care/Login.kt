package com.example.care

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_activity.*

class Login : AppCompatActivity() {
    private var StdEmail : EditText? = null
    private var StdPassword : EditText? = null
    private var StdAuth :FirebaseAuth? = null
    private var StdPrg :ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        StdEmail =findViewById(R.id.Std_email)
        StdPassword=findViewById(R.id.Std_pass)
        StdAuth= FirebaseAuth.getInstance()
        StdPrg= ProgressDialog(this)
        StdLogin.setOnClickListener {
            StdValidate()
        }
        StdSignup.setOnClickListener {
            startActivity(Intent(this,Sign_Up::class.java))
        }
        val stduser: FirebaseUser? = StdAuth?.currentUser
        if(stduser!=null){
            startActivity(Intent(this,HOME::class.java))

        }

    }

    fun StdValidate(){

        var stdemail:String =StdEmail?.text.toString()
        var stdpassword:String =StdPassword?.text.toString()
        StdPrg?.setMessage("Loging In!")
        StdPrg?.show()
        if (stdemail.isEmpty()||stdpassword.isEmpty()){
            Toast.makeText(this,"fill all feilds", Toast.LENGTH_SHORT).show()
        }else{
            StdAuth!!.signInWithEmailAndPassword(stdemail,stdpassword).addOnCompleteListener {task->
                if (task.isSuccessful){
                    StdPrg?.dismiss()
                    startActivity(Intent(this,HOME::class.java))
                }
                else{
                    StdPrg?.dismiss()
                    Toast.makeText(this, "Email or Password is incorrect", Toast.LENGTH_SHORT).show()
                }
            }




        }




    }

    fun Sign_up(v:View){
        val intent = Intent (this,Sign_Up::class.java)
        startActivity(intent)

    }
}