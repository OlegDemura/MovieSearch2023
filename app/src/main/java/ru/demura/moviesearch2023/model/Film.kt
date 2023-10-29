package ru.demura.moviesearch2023.model

class Film(val title: String, val description: String, val image:Int, var like: Boolean = false) {
    var comments: String? = null
}