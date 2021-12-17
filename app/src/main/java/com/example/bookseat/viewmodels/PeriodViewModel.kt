package com.example.bookseat.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookseat.repository.Rooms
import com.google.firebase.firestore.FirebaseFirestore


class PeriodViewModel : ViewModel() {

    private var db = FirebaseFirestore.getInstance()
    private val collection = db.collection("sede")
    val TAG = "ManageReservationFrag"

    private val _rooms = MutableLiveData<List<Rooms>>()
    val rooms: LiveData<List<Rooms>> get() = _rooms

    fun fetchRoomsNames() {
        val roomsNamesList = ArrayList<Rooms>()

        collection.addSnapshotListener{value,error ->
            if (error != null){
                Log.e("Firestore Error", error.message.toString())
                return@addSnapshotListener
            }
       if (value != null){
           val documents = value.documents
           documents.forEach{
               val rooms = it.toObject(Rooms::class.java)

               if (rooms != null){
                    rooms.roomsId = it.id
                   roomsNamesList.add(rooms)
               }
           }
           _rooms.value = roomsNamesList
       }else{
           Log.d(TAG, "No such document")
       }
        }
    }
}