package ru.demura.moviesearch2023

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.demura.moviesearch2023.model.Film

class FilmAdapter(private val mList: List<Film>, private val listener: FilmClickListener) :
    RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = mList[position]

        holder.bind(film, listener, position)

    }

    interface FilmClickListener {
        fun onDetailClick(position: Int)
        fun onFavoriteClick(filmItem: Film, position: Int)
        fun checkFavoriteList(filmItem: Film) : Boolean
    }
}