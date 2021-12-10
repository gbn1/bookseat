package com.example.bookseat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookseat.databinding.FragmentManageReservationBinding
import com.example.bookseat.repository.ListAdapter
import com.example.bookseat.repository.Reservations
import com.google.firebase.firestore.*

class ManageReservationFragment : Fragment() {

    val TAG = "ManageReservationFrag"
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var reservationArrayList: ArrayList<Reservations>
    private lateinit var listAdapter: ListAdapter
    private var db = FirebaseFirestore.getInstance()
    private lateinit var binding: FragmentManageReservationBinding
    private val collection = db.collection("prenotazioni")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentManageReservationBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.setHasFixedSize(true)

        reservationArrayList = arrayListOf()

        listAdapter = ListAdapter(reservationArrayList)

        recyclerView.adapter = listAdapter

        fetchUpdate()

    }

    private fun fetchUpdate() {

        collection.addSnapshotListener {  value, error ->
            if (error != null) {
                Log.e("Firestore Error", error.message.toString())
                return@addSnapshotListener
            }
            value?.documents?.forEach { documentSnapshot ->
                documentSnapshot.reference
                    .collection("prenotazioni").get()
                    .addOnSuccessListener { innerDocuments ->
                        if (innerDocuments != null) {
                            innerDocuments.forEach {
                                Log.e(TAG, it.id)
                                it.toObject(Reservations::class.java)
                                    .let {
                                        reservationArrayList.add(it)
                                    }
                                listAdapter.notifyDataSetChanged()
                            }
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }.addOnFailureListener {

                    }
            }
        }
    }
}