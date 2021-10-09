package com.example.care

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.care.databinding.ProfileBinding
import com.example.care.model.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.profile.*
import kotlinx.android.synthetic.main.signup_activity.*


class Profile : Fragment() {

    private lateinit var binding: ProfileBinding
    private var FP_Auth: FirebaseAuth? = null
    private var FP_Data: FirebaseDatabase? = null
    lateinit var fn: TextView
    lateinit var ln: TextView
    lateinit var stdEml: TextView
    lateinit var Sphone: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileBinding.inflate(layoutInflater)
        showdataP()
        init()
        return binding.root
    }

    private fun init() {
        FP_Auth = FirebaseAuth.getInstance()
        binding.lgout.setOnClickListener {
            FP_Auth?.signOut()
            startActivity(Intent(getActivity(), Login::class.java))
        }
        binding.Editprofile.setOnClickListener {
            startActivity(Intent(getActivity(), Edit_Profile::class.java))
        }
    }
    private fun showdataP(){

        fn = binding.Fnm
        ln = binding.Lnm
        stdEml = binding.Eml
        Sphone = binding.pno
        FP_Auth= FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().getReference().child("UserInfo").child(FP_Auth?.uid.toString())
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val std_profile =snapshot.getValue(UserInfo::class.java)
                fn.text = "First Name:  " + std_profile?.firstName
                ln.text = "last Name:  " + std_profile?.lastName
                stdEml.text = "Email:  " + std_profile?.stdEmail
                Sphone.text = "Phone no:  " + std_profile?.phone

            }

            override fun onCancelled(error: DatabaseError) {
             Toast.makeText(activity,error.code,Toast.LENGTH_SHORT).show()
            }


        })
    }
}

