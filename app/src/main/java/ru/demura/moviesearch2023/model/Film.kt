package ru.demura.moviesearch2023.model

import android.graphics.Color

class Film(val id: Int, val title: String, val description: String, val image:Int, var like: Boolean = false, var color: Int = Color.BLACK) {
    var comments: String? = null
}