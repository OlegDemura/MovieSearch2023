package ru.demura.moviesearch2023

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import ru.demura.moviesearch2023.MainActivity.Companion.EXTRA_FILM
import ru.demura.moviesearch2023.model.Film

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val imageViewDetails: ImageView = findViewById(R.id.imageViewDetails)
        val textTitleDetails: TextView = findViewById(R.id.textTitleDetails)
        val descriptionDetails: TextView = findViewById(R.id.descriptionDetails)
        val buttonInviteAFriend: View = findViewById(R.id.buttonInviteAFriend)
        val film: Film = intent.getSerializableExtra(EXTRA_FILM) as Film
        imageViewDetails.setImageResource(film.image)
        textTitleDetails.text = film.title
        descriptionDetails.text = film.description

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
                startActivity(sendIntent);
            }
        }
    }
}