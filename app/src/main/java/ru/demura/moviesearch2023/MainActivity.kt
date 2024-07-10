package ru.demura.moviesearch2023

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.demura.moviesearch2023.model.Film
import ru.demura.moviesearch2023.repository.Repository

class MainActivity : AppCompatActivity() {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val ARGS_SCROLL_STATE = "recyclerState"

    companion object {
        const val EXTRA_FILM = "EXTRA_FILM"
        const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("requestedOrientation", resources.configuration.orientation.toString())

        if (resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            recyclerView.layoutManager = LinearLayoutManager(this)
            createContext(recyclerView)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this,2)
            createContext(recyclerView)
        }

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("dialog", "handleOnBackPressed")
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Вы действительно хотите выйти?")
                    .setPositiveButton("Да") { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .setNegativeButton("Нет") { dialog, _ ->
                        dialog.cancel()
                    }
                builder.create().show()
            }
        }

        onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    private fun createContext(recyclerView: RecyclerView) {

        val data = Repository.filmList

        val favorList = Repository.favorList

        recyclerView.adapter = FilmAdapter(data, object : FilmAdapter.FilmClickListener {
            override fun onDetailClick(position: Int) {
                val detailsActivityIntent = Intent(this@MainActivity, DetailsActivity::class.java)
                detailsActivityIntent.putExtra(EXTRA_FILM, position)
                startActivityForResult(detailsActivityIntent, REQUEST_CODE)
                recyclerView.adapter?.notifyItemChanged(position) //Меняет состояние после возврата на предыдущее окно.
            }

            override fun onFavoriteClick(filmItem: Film, position: Int) {
                //Сделать передачу данных в список любимых на нажатие сердечка
                if (!favorList.contains(filmItem)) {
                    favorList.add(filmItem)
                } else {
                    favorList.remove(filmItem)
                }
                recyclerView.adapter?.notifyItemChanged(position)
            }

            override fun checkFavoriteList(filmItem: Film): Boolean {
                return favorList.contains(filmItem)
            }
        })

        val onFavoriteLink: TextView = findViewById(R.id.onFavoriteLink)
        onFavoriteLink.setOnClickListener {
            val favoriteActivityIntent = Intent(this@MainActivity, FavoriteListActivity::class.java)
            startActivity(favoriteActivityIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(ARGS_SCROLL_STATE, recyclerView.layoutManager?.onSaveInstanceState())
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(ARGS_SCROLL_STATE, "ARGS_SCROLL_STATE")
        recyclerView.layoutManager?.onRestoreInstanceState(
            savedInstanceState.getParcelable(
                ARGS_SCROLL_STATE
            )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Log.i(
                    "RESULT_FROM_DETAILS_ACTIVITY",
                    data?.getBooleanExtra(DetailsActivity.RESULT_FAVORITE, false).toString()
                )
                Log.i(
                    "RESULT_FROM_DETAILS_ACTIVITY",
                    data?.getStringExtra(DetailsActivity.RESULT_COMMENT).toString()
                )
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}