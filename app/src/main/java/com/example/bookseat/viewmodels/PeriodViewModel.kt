package com.example.bookseat.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookseat.repository.Room
import com.example.bookseat.repository.RoomDetail
import com.google.firebase.firestore.FirebaseFirestore


class PeriodViewModel : ViewModel() {

    private var db = FirebaseFirestore.getInstance()
    private val collection = db.collection("sede")
    private val bookseatsCollection = db.collection("prenotazioni")
    val TAG = "PeriodFrag"

    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms


    fun fetchRoomsNames() {
        val rooms = ArrayList<Room>()

        collection.addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("Firestore Error", error.message.toString())
                return@addSnapshotListener
            }
            if (value != null) {
                val documents = value.documents
                documents.forEach {
                    // TODO recuperare informazioni altri campi
                    it.toObject(RoomDetail::class.java)?.toRoom(it.id)?.let {
                        rooms.add(it)
                    }
                }
                _rooms.value = rooms
            } else {
                Log.d(TAG, "No such document")
            }
        }
    }

    fun fetchData(room: Room?): ArrayList<String> {
        val item = ArrayList<String>()
        if (room != null)
            for (i in 1 .. room.chairsNum) {
                item.add("$i")
            }
        return item
    }
}