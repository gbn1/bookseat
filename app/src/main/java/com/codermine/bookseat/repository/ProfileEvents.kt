package com.codermine.bookseat.repository

sealed class ProfileEvents
object GoToNewReservation : ProfileEvents()
object GoToManageReservation : ProfileEvents()