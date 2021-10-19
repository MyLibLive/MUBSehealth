package com.example.mubsehealth.model

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mubsehealth.CreateReservation
import com.example.mubsehealth.R

class BookingAdapter(r: Context, options: ArrayList<Booking>) : RecyclerView.Adapter<BookingAdapter.bookingViewHolder>(){
    var c=r
    var y = options
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookingAdapter.bookingViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.booking_list, parent, false)
        return BookingAdapter.bookingViewHolder(inflate)
    }

    class bookingViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(context: Context, date:String, name:String){

            val n = itemView.findViewById<TextView>(R.id.name)
            n.text = name

            val d = itemView.findViewById<TextView>(R.id.date)
            d.text = date


        }

    }

    override fun onBindViewHolder(holder: bookingViewHolder, position: Int) {
        Log.d("y", "$y")
        Log.d("c", "$c")
        holder.onBind(c, y[position].date!! + y[position].time, "Dr. ${y[position].dName!!}")

//        holder.itemView.setOnClickListener {
//            val intent = Intent(c, CreateReservation::class.java)
//            intent.putExtra("dId", y[position].id )
//            intent.putExtra("dPhone", y[position].phone)
//            c.startActivity(intent)
//        }

    }

    override fun getItemCount(): Int {
        return y.size
    }
}