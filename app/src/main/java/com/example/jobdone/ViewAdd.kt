package com.example.jobdone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class ViewAdd : AppCompatActivity() {
    private lateinit var tvAdId: TextView
    private lateinit var tvJobName: TextView
    private lateinit var tvJobDescription: TextView
    private lateinit var tvJobRequirements: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_add)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener { //updating
            openUpdateDialog(
                intent.getStringExtra("adId").toString(),
                intent.getStringExtra("jobName").toString()
            )
        }

        btnDelete.setOnClickListener { //deleting
            deleteRecord(
                intent.getStringExtra("adId").toString()
            )
        }

    }
    private fun openUpdateDialog(
        empId: String,
        empName: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_update_dialog, null)

        mDialog.setView(mDialogView)

        val etjobName = mDialogView.findViewById<EditText>(R.id.etjobName)
        val etjobDesc = mDialogView.findViewById<EditText>(R.id.etjobDesc)
        val etRequirements = mDialogView.findViewById<EditText>(R.id.etjobReq)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etjobName.setText(intent.getStringExtra("jobName").toString())
        etjobDesc.setText(intent.getStringExtra("jobDescription").toString())
        etRequirements.setText(intent.getStringExtra("jobRequirement").toString())

        mDialog.setTitle("Updating $empName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateEmpData(
                empId,
                etjobName.text.toString(),
                etjobDesc.text.toString(),
                etRequirements.text.toString()
            )

            Toast.makeText(applicationContext, "Advertisement Data Updated", Toast.LENGTH_LONG).show()

            //we are setting updated data to our textviews
            tvJobName.text = etjobName.text.toString()
            tvJobDescription.text =  etjobDesc.text.toString()
            tvJobRequirements.text = etRequirements.text.toString()

            alertDialog.dismiss()
        }
    }

    private fun updateEmpData(
        id: String,
        jobname: String,
        desc: String,
        requirements: String
    ) {
        val dbRef = FirebaseDatabase.getInstance().getReference("job_ads").child(id)
        val empInfo =Admodel(id, jobname, desc, requirements)
        dbRef.setValue(empInfo)
    }



    private fun initView() {
        tvAdId = findViewById(R.id.tvadId)
        tvJobName = findViewById(R.id.tvjobName)
        tvJobDescription = findViewById(R.id.tvjobDesc)
        tvJobRequirements = findViewById(R.id.tvrequirements)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvAdId.text = intent.getStringExtra("adId")
        tvJobName.text = intent.getStringExtra("jobName")
        tvJobDescription.text = intent.getStringExtra("jobDescription")
        tvJobRequirements.text = intent.getStringExtra("jobRequirement")

    }


    private fun deleteRecord(
        id: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("job_ads").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Ad Deleted", Toast.LENGTH_LONG).show()

            val intent = Intent(this, Fetching::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this, "Deleting Err ${error.message}", Toast.LENGTH_LONG).show()
        }
    }
}