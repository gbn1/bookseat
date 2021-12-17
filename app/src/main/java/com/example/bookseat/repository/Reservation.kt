package com.example.bookseat.repository

data class Reservation(
    val afternoon: Boolean?,
    val chairAftr: Int,
    val chairMorn: Int,
    val morning: Boolean?,
    val room: String
) {
    constructor() : this(null, -1, -1, null, "")
}