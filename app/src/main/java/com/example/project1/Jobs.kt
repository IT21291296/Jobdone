package com.example.project1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.project1.databinding.ActivityJobsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class Jobs : AppCompatActivity() {

    private lateinit var binding: ActivityJobsBinding
    private lateinit var database:DatabaseReference





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityJobsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dPost.setOnClickListener {
            val title=binding.djTitle.text.toString()
            val location=binding.dLocation.text.toString()
            val salary=binding.dSalary.text.toString()

            database=FirebaseDatabase.getInstance().getReference("Jobs")
            val jobcl= Jobcl(title,location,salary)
            database.child(title).setValue(jobcl).addOnSuccessListener {
                binding.djTitle.text.clear()
                binding.dLocation.text.clear()
                binding.dSalary.text.clear()

                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()


            }
        }



    }
}