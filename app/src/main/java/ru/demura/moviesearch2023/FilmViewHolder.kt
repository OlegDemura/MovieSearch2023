package ru.demura.moviesearch2023

import android.graphics.Color
import android.view.View
import android.widget.ImageSwitcher
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.demura.moviesearch2023.model.Film

class FilmViewHolder(view: View) : ViewHolder(view) {

    private val imageView = view.findViewById<ImageView>(R.id.imageView)
    private val nameFilm = view.findViewById<TextView>(R.id.nameFilm)
    private val description = view.findViewById<TextView>(R.id.description)
    private val buttonDetail = view.findViewById<View>(R.id.buttonDetail)
    private val imageLike = view.findViewById<ImageView>(R.id.imageFavorite)

    fun bind(film: Film, listener: FilmAdapter.FilmClickListener, position: Int) {
        imageView.setImageResource(film.image)
        nameFilm.text = film.title
        nameFilm.setTextColor(film.color)
        description.text = film.description
        buttonDetail.setOnClickListener {
            film.color = Color.RED
            listener.onDetailClick(position)
        }

        imageLike.setOnClickListener {
            if(imageLike.resources.equals(R.drawable.black_heart)) {
                imageLike.setImageResource(R.drawable.red_heart)
            } else {
                imageLike.setImageResource(R.drawable.black_heart)
            }
            listener.onFavoriteClick(film, position)
        }

        if (listener.checkFavoriteList(film)){
            imageLike.setImageResource(R.drawable.red_heart)
        } else {
            imageLike.setImageResource(R.drawable.black_heart)
        }
        //Еще раз пересмотреть фрагмент с -24 минуты. По поводу того как избежать переиспользоваться ItemView
        //По итогу есть только один спобоб. Передавать состояние из модели.
    }

}
