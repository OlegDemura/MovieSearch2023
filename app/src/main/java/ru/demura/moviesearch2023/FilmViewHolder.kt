package ru.demura.moviesearch2023

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.demura.moviesearch2023.MainActivity.Companion.EXTRA_FILM
import ru.demura.moviesearch2023.MainActivity.Companion.REQUEST_CODE
import ru.demura.moviesearch2023.model.Film

class FilmViewHolder(view: View) : ViewHolder(view) {

    private val imageView = view.findViewById<ImageView>(R.id.imageView)
    private val nameFilm = view.findViewById<TextView>(R.id.nameFilm)
    private val description = view.findViewById<TextView>(R.id.description)
    private val buttonDetail = view.findViewById<View>(R.id.buttonDetail)

    fun bind(film: Film, context: Activity, position: Int) {
        imageView.setImageResource(film.image)
        nameFilm.text = film.title
        description.text = film.description
        buttonDetail.setOnClickListener {
            nameFilm.setTextColor(Color.RED)
            context.startActivityForResult(
                Intent(context, DetailsActivity::class.java).putExtra(
                    EXTRA_FILM,
                    position
                ),
                REQUEST_CODE
            )
        }
    }




}
