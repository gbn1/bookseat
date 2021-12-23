package com.codermine.bookseat.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codermine.bookseat.repository.GoBackToProfile
import com.codermine.bookseat.repository.GoToChosenTime
import com.codermine.bookseat.repository.NewReservationEvents

class NewReservationViewModel : ViewModel() {
    private val _events = MutableLiveData<NewReservationEvents?>()
    val events: MutableLiveData<NewReservationEvents?> = _events

    fun onClickNext() {
        _events.value = GoToChosenTime
    }

    fun onClickBack() {
        _events.value = GoBackToProfile

    }

    fun eventCompleted() {
        _events.value = null
    }

}