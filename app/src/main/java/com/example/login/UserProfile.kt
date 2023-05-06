package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.login.databinding.ActivityEmployeeFormBinding
import com.example.login.databinding.ActivityRegistrationBinding
import com.example.login.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserProfile : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var uid: String
    private lateinit var user1:user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        database = FirebaseDatabase.getInstance().getReference("user")
        if(uid.isNotEmpty()){

            getUserData()
        }

    }

    private fun getUserData() {

      database.child(uid).addValueEventListener(object :ValueEventListener{
          override fun onDataChange(snapshot: DataSnapshot) {
              user1 =snapshot.getValue(user::class.java)!!
              binding.empName.setText(user1.ffname+""+user1.flname)
              binding.empGender.setText(user1.fgender)
              binding.empEmail.setText(user1.femail)
              binding.empAddress.setText(user1.faddress)
              binding.empPhone.setText(user1.fphone)
          }

          override fun onCancelled(error: DatabaseError) {
              Toast.makeText(this@UserProfile,"Failed to get User Profile Data",Toast.LENGTH_SHORT).show()
          }

      })


        var EmpEdit =findViewById<Button>(R.id.EmpEdit)
        EmpEdit.setOnClickListener {
            val Intent = Intent(this, UserProfileEditForEmployee::class.java )
            startActivity(Intent)
        }
    }

}
