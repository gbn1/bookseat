package com.codermine.bookseat.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codermine.bookseat.repository.*

class ProfileViewModel : ViewModel() {
    private val _events = MutableLiveData<ProfileEvents?>()
    val events: MutableLiveData<ProfileEvents?> = _events

    fun onClickNewReservation() {
        _events.value = GoToNewReservation
    }

    fun onClickManageReservation() {
        _events.value = GoToManageReservation
    }

    fun eventCompleted() {
        _events.value = null
    }
}