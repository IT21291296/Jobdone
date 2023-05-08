package com.example.project1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    private lateinit var jobBtn2:Button



        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)



            jobBtn2 = findViewById(R.id.jobBtn2)


            jobBtn2.setOnClickListener {
                val intent = Intent(this, Jobs ::class.java)
                startActivity(intent)
            }

        }


    }


