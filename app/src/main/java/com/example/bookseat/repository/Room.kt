package com.example.bookseat.repository

data class Room(
    var roomsId: String,
    val chairsNum: Int = 0,
    val hasHead: Boolean? = null,
    val hasTail: Boolean? = null,
    val isLong: Boolean? = null,
    val isVertical: Boolean? = null
)

