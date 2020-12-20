package com.example.bestgamespc

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class GameDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        val title = intent.extras?.getString("title")

        //create an instance of DataManager
        val dm = DataManager(this)

        //get all games as a cursor
        val gameCursor = dm.searchGame(title.toString())

        if (gameCursor.count > 0) {

            gameCursor.moveToNext()
            val game = Game(gameCursor.getString(1), gameCursor.getString(2),
                gameCursor.getString(3), gameCursor.getString(4),
                gameCursor.getString(5), gameCursor.getString(6))

            val textViewTitle = findViewById<TextView>(R.id.textViewTitle)
            val textViewCategory = findViewById<TextView>(R.id.textViewCategory)
            val textViewHardwareRequirements = findViewById<TextView>(R.id.textViewHardwareRequirements)
            val textViewPrice = findViewById<TextView>(R.id.textViewPrice)
            val imageViewGame = findViewById<ImageView>(R.id.imageViewGame)

            textViewTitle.text = game.title
            textViewCategory.text = game.category
            textViewHardwareRequirements.text = game.hardwareRequirements

            //Format price
            if (game.price == "FREE") {
                textViewPrice.text = "Price: \t\t" + game.price
            } else {
                textViewPrice.text = "Price: \t\t$" + game.price
            }


            val img = this.resources.getIdentifier(game.image, "drawable", this.packageName)
            imageViewGame.setImageResource(img)

            val btnDownload = findViewById<Button>(R.id.btnDownload)

            btnDownload.setOnClickListener {
                val uri = Uri.parse(game.downloadLink)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

            val btnBack = findViewById<Button>(R.id.btnBack)

            btnBack.setOnClickListener {
                finish()
            }

        } else {
            finish()
        }



    }
}