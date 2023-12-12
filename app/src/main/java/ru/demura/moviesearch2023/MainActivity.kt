package ru.demura.moviesearch2023

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        createContext()
    }

    private fun createContext() {

        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = Repository.filmList

        val adapter = FilmAdapter(data, this)

        recyclerView.adapter = adapter

        //TODO доделать сохранение состояния при перевороте экрана
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