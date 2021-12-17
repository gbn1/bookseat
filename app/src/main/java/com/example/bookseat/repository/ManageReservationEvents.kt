package com.example.bookseat.repository

sealed class ManageReservationEvents
object ReservationUpdate : ManageReservationEvents()