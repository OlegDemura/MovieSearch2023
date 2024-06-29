package ru.demura.moviesearch2023

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.demura.moviesearch2023.model.Film
import ru.demura.moviesearch2023.repository.Repository

class FavoriteListActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_favorite_list)
        if (resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            createContext(recyclerView)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this,2)
            createContext(recyclerView)
        }
    }



    fun createContext(recyclerView: RecyclerView){

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