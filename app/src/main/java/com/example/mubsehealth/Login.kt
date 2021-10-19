package com.example.mubsehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.mubsehealth.model.PrefsManager
import com.example.mubsehealth.model.Student
import com.google.android.material.button.MaterialButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {


    private val prefsManager = PrefsManager.INSTANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        prefsManager.setContext(this.application)



        val log = findViewById<Button>(R.id.login)
        log.setOnClickListener {


            val id = findViewById<EditText>(R.id.stdNo)
            val stdNo = id.text.toString()

            val pwd = findViewById<EditText>(R.id.password)
            val password = pwd.text.toString()

            if(stdNo.isNotEmpty() && password.isNotEmpty()){

                if (stdNo == "Admin" && password == "admin"){
                    startActivity(Intent(this@Login, Admin::class.java))
                    finish()
                }
                else{

                val db = FirebaseDatabase.getInstance()
                db.getReference().child("/students").child(stdNo).addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(p0: DataSnapshot) {

                        if(p0.exists()){
                        val std = p0.getValue(Student::class.java)
                        val pass = std!!.password
                        if (pass == password){
                            prefsManager.onLogin(std)
                            startActivity(Intent(this@Login, Home::class.java))
                            finish()
                        }
                        }
                        else{
                            Toast.makeText(this@Login, "No Account found", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        Toast.makeText(this@Login, "An error occurred $p0", Toast.LENGTH_LONG).show()
                    }

                })

                }
            }
            else{
                Toast.makeText(this, "Fill in the correct Details", Toast.LENGTH_LONG).show()
            }
        }

        val create = findViewById<TextView>(R.id.create)
        create.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish()
        }


        val forgot = findViewById<TextView>(R.id.forgot)
        forgot.setOnClickListener {
            startActivity(Intent(this, ResetPassword::class.java))
            finish()
        }
    }
}