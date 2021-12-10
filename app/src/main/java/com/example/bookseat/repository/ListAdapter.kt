package com.example.bookseat.repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookseat.R
import com.example.bookseat.databinding.ReservationListModelBinding

class ListAdapter(private val reservationList : ArrayList<Reservations> ) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    private lateinit var binding : ReservationListModelBinding

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
         var room : TextView = view.findViewById(R.id.room_name)
         var morning : TextView = view.findViewById(R.id.morning_reservation)
         var afternoon : TextView = view.findViewById(R.id.afternoon_reservation)
         var chairAftr : TextView = view.findViewById(R.id.chairNAfternoon)
         var chairMorn : TextView = view.findViewById(R.id.chairNMorning)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reservation_list_model,parent,false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val reservations : Reservations = reservationList[position]
        viewHolder.room.text = reservations.room
        viewHolder.morning.text = reservations.morning.toString()
        viewHolder.afternoon.text = reservations.afternoon.toString()
        viewHolder.chairAftr.text = reservations.chairAftr.toString()
        viewHolder.chairMorn.text = reservations.chairMorn.toString()
    }

    override fun getItemCount(): Int {
       return reservationList.size
    }

}