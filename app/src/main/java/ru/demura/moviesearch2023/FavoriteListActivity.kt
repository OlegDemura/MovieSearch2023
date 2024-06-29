package ru.demura.moviesearch2023

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.demura.moviesearch2023.R
import ru.demura.moviesearch2023.model.Film
import ru.demura.moviesearch2023.repository.Repository

class FavoriteListActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_list)
        createContext()
    }



    fun createContext(){
        Log.d("createContext1", "one")
        recyclerView.layoutManager = LinearLayoutManager(this)

        Log.d("createContext1", "two")
        val favorList = Repository.favorList

        recyclerView.adapter = FilmAdapter(favorList, object : FilmAdapter.FilmClickListener {
            override fun onDetailClick(position: Int) {
                val detailsActivityIntent = Intent(this@FavoriteListActivity, DetailsActivity::class.java)
                detailsActivityIntent.putExtra(MainActivity.EXTRA_FILM, position)
                startActivityForResult(detailsActivityIntent, MainActivity.REQUEST_CODE)
                recyclerView.adapter?.notifyItemChanged(position) //Меняет состояние после возврата на предыдущее окно.
            }

            override fun onFavoriteClick(filmItem: Film, position: Int) {
                //Сделать передачу данных в список любимых на нажатие сердечка
                if (!favorList.contains(filmItem)) {
                    favorList.add(filmItem)
                } else {
                    favorList.remove(filmItem)
                }
                recyclerView.adapter?.notifyItemRemoved(position)
            }

            override fun checkFavoriteList(filmItem: Film): Boolean {
                return favorList.contains(filmItem)
            }
        })

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val detailsActivityIntent = Intent(this@FavoriteListActivity, MainActivity::class.java)
                startActivityForResult(detailsActivityIntent, MainActivity.REQUEST_CODE)
            }
        })

    }

}