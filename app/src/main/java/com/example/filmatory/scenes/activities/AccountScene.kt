package com.example.filmatory.scenes.activities

import android.content.Intent
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountController
import com.example.filmatory.scenes.SuperScene

import com.example.filmatory.utils.BlurImage

class AccountScene : SuperScene() {
    private lateinit var accountController: AccountController
    private var blurImage: BlurImage = BlurImage()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        accountController = AccountController(this)
        blurImage.blurImage(this, R.drawable.image10, findViewById(R.id.account_imgView))

        var accountCardView = findViewById<CardView>(R.id.account_cardOne)
        var favoriteCardView = findViewById<CardView>(R.id.account_cardTwo)
        var watchlistCardView = findViewById<CardView>(R.id.account_cardThree)
        var mylistsCardView = findViewById<CardView>(R.id.account_cardFour)

        accountCardView.setOnClickListener {
            val intent = Intent(this, AccountInfoScene::class.java)
            intent.putExtra("position", 0)
            startActivity(intent)
        }
        favoriteCardView.setOnClickListener {
            val intent = Intent(this, AccountInfoScene::class.java)
            intent.putExtra("position", 1)
            startActivity(intent)
        }
        watchlistCardView.setOnClickListener {
            val intent = Intent(this, AccountInfoScene::class.java)
            intent.putExtra("position", 2)
            startActivity(intent)
        }
        mylistsCardView.setOnClickListener {
            val intent = Intent(this, AccountInfoScene::class.java)
            intent.putExtra("position", 3)
            startActivity(intent)
        }
    }
}

