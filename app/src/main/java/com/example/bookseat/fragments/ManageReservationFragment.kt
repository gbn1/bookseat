package com.example.bookseat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ContentView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookseat.databinding.FragmentManageReservationBinding
import com.example.bookseat.repository.ListAdapter
import com.example.bookseat.repository.Reservations
import com.google.firebase.firestore.*

class ManageReservationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var reservationArrayList: ArrayList<Reservations>
    private lateinit var listAdapter: ListAdapter
    private lateinit var db : FirebaseFirestore
    private lateinit var binding: FragmentManageReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentManageReservationBinding.inflate(layoutInflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerView

        recyclerView.layoutManager

        //recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        reservationArrayList = arrayListOf()

        listAdapter = ListAdapter(reservationArrayList)

        recyclerView.adapter = listAdapter

        EventChangeListener()

    }

    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("sede").
        addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value:QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error != null){
                    Log.e("Firstore Error",error.message.toString())
                    return
                }
                for (dc : DocumentChange in value?.documentChanges!!){

                    if(dc.type == DocumentChange.Type.ADDED){
                        reservationArrayList.add(dc.document.toObject(Reservations::class.java))
                    }
                }

                listAdapter.notifyDataSetChanged()
            }

        })
    }


}