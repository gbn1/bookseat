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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.reservation_list_model,parent,false)

        return ViewHolder((itemView))
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val reservations : Reservations = reservationList[position]
        holder.chairNumber.text = reservations.chairsNum.toString()
        holder.isLong.text = reservations.isVertical.toString()
        holder.isVertical.text = reservations.isLong.toString()
    }

    override fun getItemCount(): Int {
       return reservationList.size
    }

    public class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

        val chairNumber : TextView = itemView.findViewById(R.id.selected_place)
        val isVertical : TextView = itemView.findViewById(R.id.room_name)
        val isLong : TextView = itemView.findViewById(R.id.place_number)

    }
}