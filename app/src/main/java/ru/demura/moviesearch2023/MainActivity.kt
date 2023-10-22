package ru.demura.moviesearch2023

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.demura.moviesearch2023.repository.Repository

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_FILM = "EXTRA_FILM"
    }

    private lateinit var name1: TextView
    private lateinit var name2: TextView
    private lateinit var name3: TextView
    private lateinit var name4: TextView
    private lateinit var name5: TextView
    private lateinit var name6: TextView

    private val NAME1 = "NAME1"
    private val NAME2 = "NAME2"
    private val NAME3 = "NAME3"
    private val NAME4 = "NAME4"
    private val NAME5 = "NAME5"
    private val NAME6 = "NAME6"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createContext()
    }

    private fun createContext() {
        val imageView1: ImageView = findViewById(R.id.imageView1)
        name1 = findViewById(R.id.name1)
        val description1: TextView = findViewById(R.id.description1)
        val button1: View = findViewById(R.id.button1)
        name1.text = Repository.filmList[0].title
        description1.text = Repository.filmList[0].description
        imageView1.setImageResource(Repository.filmList[0].image)
        button1.setOnClickListener {
            val randomIntent = Intent(this, DetailsActivity::class.java)
            name1.setTextColor(Color.RED)
            randomIntent.putExtra(EXTRA_FILM, Repository.filmList[0])
            startActivity(randomIntent)
        }

        val imageView2: ImageView = findViewById(R.id.imageView2)
        name2 = findViewById(R.id.name2)
        val description2: TextView = findViewById(R.id.description2)
        val button2: View = findViewById(R.id.button2)
        name2.text = Repository.filmList[1].title
        description2.text = Repository.filmList[1].description
        imageView2.setImageResource(Repository.filmList[1].image)
        button2.setOnClickListener {
            val randomIntent = Intent(this, DetailsActivity::class.java)
            name2.setTextColor(Color.RED)
            randomIntent.putExtra(EXTRA_FILM, Repository.filmList[1])
            startActivity(randomIntent)
        }

        val imageView3: ImageView = findViewById(R.id.imageView3)
        name3 = findViewById(R.id.name3)
        val description3: TextView = findViewById(R.id.description3)
        val button3: View = findViewById(R.id.button3)
        name3.text = Repository.filmList[2].title
        description3.text = Repository.filmList[2].description
        imageView3.setImageResource(Repository.filmList[2].image)
        button3.setOnClickListener {
            val randomIntent = Intent(this, DetailsActivity::class.java)
            name3.setTextColor(Color.RED)
            randomIntent.putExtra(EXTRA_FILM, Repository.filmList[2])
            startActivity(randomIntent)
        }

        val imageView4: ImageView = findViewById(R.id.imageView4)
        name4 = findViewById(R.id.name4)
        val description4: TextView = findViewById(R.id.description4)
        val button4: View = findViewById(R.id.button4)
        name4.text = Repository.filmList[3].title
        description4.text = Repository.filmList[3].description
        imageView4.setImageResource(Repository.filmList[3].image)
        button4.setOnClickListener {
            val randomIntent = Intent(this, DetailsActivity::class.java)
            name4.setTextColor(Color.RED)
            randomIntent.putExtra(EXTRA_FILM, Repository.filmList[3])
            startActivity(randomIntent)
        }

        val imageView5: ImageView = findViewById(R.id.imageView5)
        name5 = findViewById(R.id.name5)
        val description5: TextView = findViewById(R.id.description5)
        val button5: View = findViewById(R.id.button5)
        name5.text = Repository.filmList[4].title
        description5.text = Repository.filmList[4].description
        imageView5.setImageResource(Repository.filmList[4].image)
        button5.setOnClickListener {
            val randomIntent = Intent(this, DetailsActivity::class.java)
            name5.setTextColor(Color.RED)
            randomIntent.putExtra(EXTRA_FILM, Repository.filmList[4])
            startActivity(randomIntent)
        }

        val imageView6: ImageView = findViewById(R.id.imageView6)
        name6 = findViewById(R.id.name6)
        val description6: TextView = findViewById(R.id.description6)
        val button6: View = findViewById(R.id.button6)
        name6.text = Repository.filmList[5].title
        description6.text = Repository.filmList[5].description
        imageView6.setImageResource(Repository.filmList[5].image)
        button6.setOnClickListener {
            val randomIntent = Intent(this, DetailsActivity::class.java)
            name6.setTextColor(Color.RED)
            randomIntent.putExtra(EXTRA_FILM, Repository.filmList[5])
            startActivity(randomIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putInt(NAME1, name1.currentTextColor)
            putInt(NAME2, name2.currentTextColor)
            putInt(NAME3, name3.currentTextColor)
            putInt(NAME4, name4.currentTextColor)
            putInt(NAME5, name5.currentTextColor)
            putInt(NAME6, name6.currentTextColor)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        name1.setTextColor(savedInstanceState.getInt(NAME1))
        name2.setTextColor(savedInstanceState.getInt(NAME2))
        name3.setTextColor(savedInstanceState.getInt(NAME3))
        name4.setTextColor(savedInstanceState.getInt(NAME4))
        name5.setTextColor(savedInstanceState.getInt(NAME5))
        name6.setTextColor(savedInstanceState.getInt(NAME6))
    }
}