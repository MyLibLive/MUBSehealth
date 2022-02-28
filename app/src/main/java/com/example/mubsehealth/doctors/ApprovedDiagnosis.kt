package com.example.mubsehealth.doctors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mubsehealth.R
import com.example.mubsehealth.model.Diagnosis
import com.example.mubsehealth.model.DiagnosisAdapter
import com.example.mubsehealth.model.PrefsManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dmax.dialog.SpotsDialog

class ApprovedDiagnosis : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE
    lateinit var dialog: android.app.AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_approved_diagnosis)

        prefsManager.setContext(this.application)

        val dID = prefsManager.getDoctor().id

        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            startActivity(Intent(this, DoctorsHome::class.java))
        }

        dialog = SpotsDialog.Builder().setContext(this).build()

        dialog.show()


        val p = ArrayList<Diagnosis>()
        val dbRef = FirebaseDatabase.getInstance().getReference("/diagnosis").child(dID!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dialog.dismiss()
                for (y in snapshot.children){

                    Log.d("y", "$y")
                    val z = y.getValue(Diagnosis::class.java)
                    //Log.d("z", "${z!!.imgUrl}")

                    val index = p.size - 1
                    p.add(z!!)
                    //p.add(index,  z!!)
                    Log.d("p", "$p")
                }
                val adapter = DiagnosisAdapter(this@ApprovedDiagnosis, p)
                rView.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
                Toast.makeText(this@ApprovedDiagnosis, "An $error occured", Toast.LENGTH_LONG).show()
            }

        })


    }
}