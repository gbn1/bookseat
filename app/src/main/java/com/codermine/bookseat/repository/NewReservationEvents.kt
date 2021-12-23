package com.codermine.bookseat.repository

sealed class NewReservationEvents
object GoToChosenTime : NewReservationEvents()
object GoBackToProfile : NewReservationEvents()