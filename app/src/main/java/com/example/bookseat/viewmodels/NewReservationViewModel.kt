package com.example.bookseat.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bookseat.repository.GoBackToProfile
import com.example.bookseat.repository.GoToChosenTime
import com.example.bookseat.repository.NewReservationEvents
import com.example.bookseat.repository.ProfileEvents

class NewReservationViewModel : ViewModel() {
    private val _events = MutableLiveData<NewReservationEvents?>()
    val events: MutableLiveData<NewReservationEvents?> = _events

    fun onClickNext(){
        _events.value = GoToChosenTime
    }
    fun onClickBack(){
        _events.value = GoBackToProfile

    }
    fun eventCompleted() {
        _events.value = null
    }

}