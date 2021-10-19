package com.example.mubsehealth

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mubsehealth.model.Booking
import com.example.mubsehealth.model.PrefsManager
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class CreateReservation : AppCompatActivity() {

    private val prefsManager = PrefsManager.INSTANCE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_reservation)

        prefsManager.setContext(this.application)


        val image = findViewById<ImageView>(R.id.imageView2)
        image.setOnClickListener {
            startActivity(Intent(this, Reservations::class.java))
            finish()
        }


        val dName = intent.getStringExtra("name")
        val dPhone = intent.getStringExtra("dPhone")

        val date = findViewById<EditText>(R.id.date)
        val time = findViewById<EditText>(R.id.time)
        val purpose = findViewById<EditText>(R.id.purpose)

//        date.setOnClickListener {
//            val currentDate = Calendar.getInstance()
//            val year = currentDate.get(Calendar.YEAR)
//            val month = currentDate.get(Calendar.MONTH)
//            val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)
//            SupportedDatePickerDialog(this, R.style.SpinnerDatePickerDialogTheme, this, year, month, dayOfMonth).show()
//
//        }
//
//        time.setOnClickListener {
//            val currentDate = Calendar.getInstance()
//            val month = currentDate.get(Calendar.MONTH)
//            val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)
//            SupportedTimePickerDialog(
//                context = this,
//                themeResId =  R.style.SpinnerTimePickerDialogTheme,
//                timeSetListener = this,
//                mInitialHourOfDay = month,
//                mInitialMinute = dayOfMonth,
//                mIs24HourView = true)
//                .show()
//        }


        val create = findViewById<Button>(R.id.create)
        create.setOnClickListener {

            val d = date.text.toString()
            val t = time.text.toString()
            val p = purpose.text.toString()

            if (d.isNotEmpty() && t.isNotEmpty() && p.isNotEmpty()){
                val db = FirebaseDatabase.getInstance()
                //val k = db.reference.key

                val k = UUID.randomUUID().toString()

                val id = prefsManager.getStudent().id
                Log.d("did", "$dName")
                Log.d("dphone", "$dPhone")
                val ref = db.getReference("/reservations").child(id!!).child(k).setValue(Booking(d,t,p,dName!!, dPhone!!))

                if (ref.isCanceled){
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG).show()
                }
                else{

                    AlertDialog.Builder(this).setMessage("Reservation created").setPositiveButton("OK", object :DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            startActivity(Intent(this@CreateReservation, Home::class.java))
                            finish()
                        }

                    }).show()
//                    startActivity(Intent(this, Home::class.java))
//                    finish()
                }
            }
            else{
                Toast.makeText(this, "Kindly fill in the correct credentials", Toast.LENGTH_LONG).show()

            }
        }
    }
}