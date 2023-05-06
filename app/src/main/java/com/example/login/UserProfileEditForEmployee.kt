package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.login.databinding.ActivityUserProfileBinding
import com.example.login.databinding.ActivityUserProfileEditForEmployeeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class UserProfileEditForEmployee : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileEditForEmployeeBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var user1:user

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileEditForEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        database = FirebaseDatabase.getInstance().getReference("user")
        if(uid.isNotEmpty()){

            getUserData()
        }


        binding.EmpSave.setOnClickListener{

            val name = binding.Ename.text.toString()
            val gender = binding.gender.text.toString()
            val email = binding.Eemail.text.toString()
            val address = binding.Eaddress.text.toString()
            val phone = binding.Ephone.text.toString()

            val editMap = mapOf(
                "name" to name,
                "email" to email,
                "address" to address,
                "gender" to gender,
                "phone" to phone
            )

            auth = FirebaseAuth.getInstance()
            uid = auth.currentUser?.uid.toString()

            database.child("user").child(uid).updateChildren(editMap)

        }


    }

    private fun getUserData() {

        database.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user1 =snapshot.getValue(user::class.java)!!
                binding.Ename.setText(user1.ffname+""+user1.flname)
                binding.gender.setText(user1.fgender)
                binding.Eemail.setText(user1.femail)
                binding.Eaddress.setText(user1.faddress)
                binding.Ephone.setText(user1.fphone)



            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UserProfileEditForEmployee,"Failed to get User Profile Data", Toast.LENGTH_SHORT).show()
            }



        })
        binding.Edelete.setOnClickListener {
            val user = Firebase.auth.currentUser
            user?.delete()?.addOnCompleteListener {

                if(it.isSuccessful){
                    //account already deleted
                    Toast.makeText(this,"Account successfully deleted",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }else{
                    //catch error
                    Log.e("error",it.exception.toString())
                }
            }
        }


    }




}