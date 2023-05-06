package com.example.jobdone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobdone.adapters.AdfetchAdapter
import com.google.firebase.database.*

class Fetching : AppCompatActivity() {

    private lateinit var adRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var adList: ArrayList<Admodel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch)

        adRecyclerView = findViewById(R.id.rvad)
        adRecyclerView.layoutManager = LinearLayoutManager(this)
        adRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        adList = arrayListOf<Admodel>()

        getadData()

    }

    private fun getadData() {

        adRecyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("job_ads")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                adList.clear()
                if (snapshot.exists()){
                    for (adSnap in snapshot.children){
                        val adData = adSnap.getValue(Admodel::class.java)
                        adList.add(adData!!)
                    }
                    val mAdapter = AdfetchAdapter(adList)
                    adRecyclerView.adapter = mAdapter

                    mAdapter.setonItemClickListener(object : AdfetchAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@Fetching,  ViewAdd::class.java)

                            //put extras
                            intent.putExtra("AdId", adList[position].adID)
                            intent.putExtra("jobName", adList[position].jobName)
                            intent.putExtra("job_description", adList[position].jobDescription)
                            intent.putExtra("job_requirements", adList[position].jobRequirements)
                            startActivity(intent)
                        }

                    })

                    adRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}