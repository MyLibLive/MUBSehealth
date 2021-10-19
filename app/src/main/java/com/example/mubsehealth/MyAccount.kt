package com.example.mubsehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.mubsehealth.model.PrefsManager

class MyAccount : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)

        prefsManager.setContext(this.application)

        val name = findViewById<TextView>(R.id.name)
        val stdNo = findViewById<TextView>(R.id.id)
        val course = findViewById<TextView>(R.id.course)
        val email = findViewById<TextView>(R.id.email)

        val student = prefsManager.getStudent()
        name.text = "$student.firstName  ${student.lastName}"
        stdNo.text = student.id
        course.text = student.course
        email.text = student.email

        val logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            prefsManager.onLogout()
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}