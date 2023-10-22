package ru.demura.moviesearch2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import ru.demura.moviesearch2023.MainActivity.Companion.EXTRA_FILM
import ru.demura.moviesearch2023.model.Film

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val imageViewDetails: ImageView = findViewById(R.id.imageViewDetails)
        val textTitleDetails: TextView = findViewById(R.id.textTitleDetails)
        val descriptionDetails: TextView = findViewById(R.id.descriptionDetails)
        val film: Film = intent.getSerializableExtra(EXTRA_FILM) as Film
        imageViewDetails.setImageResource(film.image)
        textTitleDetails.text = film.title
        descriptionDetails.text = film.description
    }
}