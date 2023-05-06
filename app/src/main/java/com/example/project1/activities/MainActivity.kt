package com.example.project1.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.project1.R

class MainActivity : AppCompatActivity() {

    private lateinit var comButton:Button
    private lateinit var jobBtn2:Button
    private lateinit var salButton: Button
    private lateinit var evenButton: Button

        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            comButton = findViewById(R.id.comButton)


            comButton.setOnClickListener {
                val intent = Intent(this, FetchingActivity::class.java)
                startActivity(intent)
            }


            jobBtn2 = findViewById(R.id.jobBtn2)


            jobBtn2.setOnClickListener {
                val intent = Intent(this, Jobs::class.java)
                startActivity(intent)
            }



            salButton = findViewById(R.id.salButton)


            salButton.setOnClickListener {
                val intent = Intent(this, UpDel::class.java)
                startActivity(intent)
            }



            evenButton = findViewById(R.id.evenButton)


            evenButton.setOnClickListener {
                val intent = Intent(this, Honav::class.java)
                startActivity(intent)
            }







        }


    }


