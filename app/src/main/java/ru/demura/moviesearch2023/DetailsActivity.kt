package ru.demura.moviesearch2023

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import ru.demura.moviesearch2023.MainActivity.Companion.EXTRA_FILM
import ru.demura.moviesearch2023.repository.Repository

class DetailsActivity : AppCompatActivity() {

    companion object {
        val RESULT_FAVORITE = "favorite"
        val RESULT_COMMENT = "comment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val imageViewDetails: ImageView = findViewById(R.id.imageViewDetails)
        val textTitleDetails: TextView = findViewById(R.id.textTitleDetails)
        val descriptionDetails: TextView = findViewById(R.id.descriptionDetails)
        val buttonInviteAFriend: View = findViewById(R.id.buttonInviteAFriend)
        val checkBoxLike: CheckBox = findViewById(R.id.checkBoxLike)
        val comment: EditText = findViewById(R.id.comment)
        val film = Repository.filmList[intent.getIntExtra(EXTRA_FILM, 0)]
        imageViewDetails.setImageResource(film.image)
        textTitleDetails.text = film.title
        descriptionDetails.text = film.description
        film.comments?.let {
            comment.setText(it)
        }

        checkBoxLike.isChecked = film.like

        buttonInviteAFriend.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 100)
            } else {
                val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"))
                sendIntent.putExtra("sms_body", "Пойдем на фильм \"${film.title}\"?")
                startActivity(sendIntent)
            }
        }

        val result = Intent().apply {
            checkBoxLike.setOnCheckedChangeListener { _, isChecked ->
                film.like = isChecked
                putExtra(RESULT_FAVORITE, isChecked)
            }
            comment.addTextChangedListener {
                film.comments = it.toString()
                putExtra(RESULT_COMMENT, it.toString())
            }
        }
        setResult(RESULT_OK, result)
        //TODO возможно переделать получени результата на https://habr.com/ru/companies/e-legion/articles/545934/

    }

}