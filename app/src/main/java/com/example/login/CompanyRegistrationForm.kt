package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivityCompanyRegistrationFormBinding
import com.example.login.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CompanyRegistrationForm : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityCompanyRegistrationFormBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyRegistrationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()


        binding.textView8.setOnClickListener {
            val intent = Intent(this,  AdminLogin::class.java)
            startActivity(intent)
        }
        binding.RSignin.setOnClickListener {
            val name = binding.RName.text.toString()
            val email = binding.REmail.text.toString()
            val pass = binding.RPassword.text.toString()
            val confirmPass = binding.RrePass.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            val intent = Intent(this,  MainActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Empty Fields Are Not Allowed !!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}