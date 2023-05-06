package com.example.jobdone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PostAd : AppCompatActivity() {

    private lateinit var jobname: EditText
    private lateinit var jobdescription: EditText
    private lateinit var jobrequirements: EditText
    private lateinit var btnSaveData: Button

    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_ad)

        jobname = findViewById(R.id.enter_JobName)
        jobdescription = findViewById(R.id.enter_JobDescription)
        jobrequirements = findViewById(R.id.enter_JobRequirements)
        btnSaveData = findViewById(R.id.btnSave)

        dbRef = FirebaseDatabase.getInstance().getReference("job_ads")

        btnSaveData.setOnClickListener {
            saveAdData()
        }
    }

    private fun saveAdData() {

        //getting values
        val jobName = jobname.text.toString()
        val jobDescription = jobdescription.text.toString()
        val jobRequirements = jobrequirements.text.toString()

        if (jobName.isEmpty()) { //form validations
            jobname.error = "Please enter job name"
        }
        if (jobDescription.isEmpty()) {
            jobdescription.error = "Please enter jon description"
        }
        if (jobRequirements.isEmpty()) {
            jobrequirements.error = "Please enter requirements"
        }

        val adID = dbRef.push().key!!

        val ad = Admodel(adID, jobName, jobDescription, jobRequirements)

        dbRef.child(adID).setValue(ad)
            .addOnCompleteListener {
                Toast.makeText(this, "Ad posted successfully", Toast.LENGTH_LONG).show() //success message

                jobname.text.clear()
                jobdescription.text.clear()
                jobrequirements.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

}