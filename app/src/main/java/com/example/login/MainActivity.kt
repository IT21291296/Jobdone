package com.example.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.view.View
import android.widget.Toast
import com.example.login.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.ref.Reference

//import kotlinx.android.synthetic.main.activity_spinner_tutorial.*

class MainActivity : AppCompatActivity() {

    //val dropDownList = arrayOf("Admin Login","User Login")
    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView2.setOnClickListener {
            val intent = Intent(this,  registration::class.java)
            startActivity(intent)
        }
        binding.AL.setOnClickListener {
            val intent = Intent(this,  AdminLogin::class.java)
            startActivity(intent)
        }
        binding.LOGIN.setOnClickListener {


            val email = binding.UserName.text.toString()
            val pass = binding.LoginPassword.text.toString()


            if ( email.isNotEmpty() && pass.isNotEmpty() ) {

                    firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            val intent = Intent(this,  UserProfile::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, "Invalid Username Password !!", Toast.LENGTH_SHORT).show()
                        }
                    }

                            }
            else{
                Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }
        // val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,dropDownList)
        /*adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        statusFilter.adapter = adapter
        statusFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }*/

//        var LOGIN =findViewById<Button>(R.id.LOGIN)
//        LOGIN.setOnClickListener {
//            val intent1 = Intent(this, UserProfile::class.java )
//            startActivity(intent1)
//        }

    }
        }






