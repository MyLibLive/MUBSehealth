package com.example.mubsehealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mubsehealth.model.Doctor
import com.example.mubsehealth.model.DoctorAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SelectDoctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_doctor)

        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)
        //val o = FirebaseRecyclerOptions.Builder<Doctor>().setQuery(dbRef, Doctor::class.java).build()

        val p = ArrayList<Doctor>()
        val dbRef = FirebaseDatabase.getInstance().getReference("/doctors").addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (y in snapshot.children){
                    Log.d("y", "$y")
                    val z = y.getValue(Doctor::class.java)
                    Log.d("z", "${z!!.imgUrl}")
                    p.add(z)
                    Log.d("p", "$p")
                }
                val adapter = DoctorAdapter(this@SelectDoctor, p)
                rView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SelectDoctor, "An $error occured", Toast.LENGTH_LONG).show()
            }

        })
    }
}