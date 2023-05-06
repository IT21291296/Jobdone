package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivityCompanyFormBinding
import com.example.login.databinding.ActivityEmployeeFormBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CompanyForm : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityCompanyFormBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompanyFormBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.Csubmit.setOnClickListener {
            val name = binding.CName.text.toString()
            val email = binding.Cemail.text.toString()
            val address = binding.CAddress.text.toString()
            val phone = binding.CPhone.text.toString()


            database = FirebaseDatabase.getInstance().getReference("ComUser")
            val user = user(name, email, address, phone)
            database.child(email).setValue(user).addOnSuccessListener {

                binding.CName.text.clear()
                binding.Cemail.text.clear()
                binding.CAddress.text.clear()
                binding.CPhone.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()

            }
        }
    }
}