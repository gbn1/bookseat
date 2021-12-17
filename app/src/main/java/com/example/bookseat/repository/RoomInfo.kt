package com.example.bookseat.repository

data class RoomInfo (
    val chairsNum: Int,
    val hasHead: Boolean?,
    val hasTail: Boolean?,
    val isLong: Boolean?,
    val isVertical:Boolean?
        ){
    constructor(): this(0,null,null,null,null)
}