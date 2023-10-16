package ru.demura.moviesearch2023

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.demura.moviesearch2023.repository.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView1: ImageView = findViewById(R.id.imageView1)
        val name1: TextView = findViewById(R.id.name1)
        val description1: TextView = findViewById(R.id.description1)
        name1.text = Repository.filmList[0].title
        description1.text = Repository.filmList[0].description
        imageView1.setImageResource(Repository.filmList[0].image)

        val imageView2: ImageView = findViewById(R.id.imageView2)
        val name2: TextView = findViewById(R.id.name2)
        val description2: TextView = findViewById(R.id.description2)
        name2.text = Repository.filmList[1].title
        description2.text = Repository.filmList[1].description
        imageView2.setImageResource(Repository.filmList[1].image)

        val imageView3: ImageView = findViewById(R.id.imageView3)
        val name3: TextView = findViewById(R.id.name3)
        val description3: TextView = findViewById(R.id.description3)
        name3.text = Repository.filmList[2].title
        description3.text = Repository.filmList[2].description
        imageView3.setImageResource(Repository.filmList[2].image)

        val imageView4: ImageView = findViewById(R.id.imageView4)
        val name4: TextView = findViewById(R.id.name4)
        val description4: TextView = findViewById(R.id.description4)
        name4.text = Repository.filmList[3].title
        description4.text = Repository.filmList[3].description
        imageView4.setImageResource(Repository.filmList[3].image)

        val imageView5: ImageView = findViewById(R.id.imageView5)
        val name5: TextView = findViewById(R.id.name5)
        val description5: TextView = findViewById(R.id.description5)
        name5.text = Repository.filmList[4].title
        description5.text = Repository.filmList[4].description
        imageView5.setImageResource(Repository.filmList[4].image)

        val imageView6: ImageView = findViewById(R.id.imageView6)
        val name6: TextView = findViewById(R.id.name6)
        val description6: TextView = findViewById(R.id.description6)
        name6.text = Repository.filmList[5].title
        description6.text = Repository.filmList[5].description
        imageView6.setImageResource(Repository.filmList[5].image)
    }


}