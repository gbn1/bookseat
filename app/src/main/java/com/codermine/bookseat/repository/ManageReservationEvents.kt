package com.codermine.bookseat.repository

sealed class ManageReservationEvents
object ReservationUpdate : ManageReservationEvents()