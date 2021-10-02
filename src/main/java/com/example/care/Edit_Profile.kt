package com.example.care


import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.edit_profile.*


class Edit_Profile :AppCompatActivity() {
    private var newstd_Fname : EditText? = null
    private var newstd_lname : EditText? = null
    private var newstd_email : EditText? = null
    private var newstd_phone : EditText? = null
    private var New_PF : FirebaseAuth? = null  // edit profile update firebase variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile)
        newstd_Fname = findViewById(R.id.edit_first_name)
        newstd_lname = findViewById(R.id.edit_last_name)
        newstd_email = findViewById(R.id.edit_email)
        newstd_phone = findViewById(R.id.edit_Phn_no)
        New_PF = FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(New_PF?.uid.toString())
        save_Edit_std.setOnClickListener {
            val firstname = newstd_Fname?.text.toString()
            val lastname = newstd_lname?.text.toString()
            val email = newstd_email?.text.toString()
            val phone = newstd_phone?.text.toString()

            if (email.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "fill all feilds", Toast.LENGTH_SHORT).show()
            } else {

                val newstd_data = com.example.care.model.UserInfo(firstname, lastname, email, phone)
                databaseReference.setValue(newstd_data)
                startActivity(Intent(this, Profile::class.java))
            }

        }




    }
}