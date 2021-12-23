package com.codermine.bookseat.repository

data class RoomDetail (
    val chairsNum: Int = 0,
    val hasHead: Boolean? = null,
    val hasTail: Boolean? = null,
    val isLong: Boolean? = null,
    val isVertical: Boolean? = null
){
    fun toRoom(id: String): Room{
        return Room(
            roomsId = id,
            hasHead = hasHead,
            hasTail = hasTail,
            isLong = isLong,
            isVertical = isVertical,
            chairsNum = chairsNum
        )
    }
}