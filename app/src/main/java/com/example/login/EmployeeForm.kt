package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.example.login.databinding.ActivityEmployeeFormBinding
import com.example.login.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EmployeeForm : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityEmployeeFormBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)


        binding = ActivityEmployeeFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()


        binding.Submit.setOnClickListener {
            val fname = binding.FFName.text.toString()
            val lname = binding.FLName.text.toString()
            val gender = binding.FGender.text.toString()
            val email = binding.FEmail.text.toString()
            val address = binding.FAddress.text.toString()
            val phone = binding.FPhone.text.toString()


            database= FirebaseDatabase.getInstance().getReference("user")
            val user = user(fname,lname,email,gender,address,phone)
            database.child(uid).setValue(user).addOnSuccessListener {

            binding.FFName.text.clear()
            binding.FLName.text.clear()
            binding.FGender.text.clear()
            binding.FEmail.text.clear()
            binding.FAddress.text.clear()
            binding.FPhone.text.clear()

            Toast.makeText(this,"Successfully Saved",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

            }

    }
}}