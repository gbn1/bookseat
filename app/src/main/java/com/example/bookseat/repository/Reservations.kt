package com.example.bookseat.repository

data class Reservations(val afternoon : Boolean?, val chairAftr : Int, val chairMorn : Int, val morning : Boolean?, val room : String){
    constructor() : this(null, -1, -1, null,"")
}