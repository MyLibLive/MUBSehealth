package com.example.mubsehealth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        val b = findViewById<ImageView>(R.id.imageView2)
//        b.setOnClickListener {
//            startActivity(Intent(this, Login::class.java))
//            finish()
//        }

        val c = findViewById<CardView>(R.id.cardView)
        c.setOnClickListener {
            startActivity(Intent(this, Reservations::class.java))

        }

        val d = findViewById<CardView>(R.id.cardView2)
        d.setOnClickListener {
            startActivity(Intent(this, SelectDoctor::class.java))
            finish()
        }

        val doctors = findViewById<CardView>(R.id.doctors)
        doctors.setOnClickListener {
            startActivity(Intent(this, SelectDoctor::class.java))

        }

        val account = findViewById<CardView>(R.id.account)
        account.setOnClickListener {
            startActivity(Intent(this, MyAccount::class.java))

        }


    }
}