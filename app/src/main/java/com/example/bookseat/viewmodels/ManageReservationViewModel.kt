package com.example.bookseat.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookseat.repository.Reservation
import com.google.firebase.firestore.FirebaseFirestore

class ManageReservationViewModel : ViewModel() {
    private val _events = MutableLiveData<ArrayList<Reservation>>()
    val events: MutableLiveData<ArrayList<Reservation>> = _events
    val TAG = "ManageReservationFrag"
    private var db = FirebaseFirestore.getInstance()
    private val collection = db.collection("prenotazioni")

    fun fetchUpdate() {

        val reservationArrayList = arrayListOf<Reservation>()

        collection.addSnapshotListener { value, error ->
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
                                it.toObject(Reservation::class.java)
                                    .let {
                                        reservationArrayList.add(it)
                                    }
                            }
                            _events.value = reservationArrayList
                        } else {
                            Log.d(TAG, "No such document")
                        }
                    }.addOnFailureListener {

                    }
            }

        }

    }
}