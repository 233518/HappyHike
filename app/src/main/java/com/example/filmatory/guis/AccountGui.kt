package com.example.filmatory.guis

import android.content.Intent
import androidx.cardview.widget.CardView
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountController
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.scenes.activities.AccountScene

class AccountGui(private val accountScene: AccountScene, private val accountController: AccountController) {
    var accountCardView: CardView = accountScene.findViewById(R.id.account_cardOne)
    var favoriteCardView: CardView = accountScene.findViewById(R.id.account_cardTwo)
    var watchlistCardView: CardView = accountScene.findViewById(R.id.account_cardThree)
    var mylistsCardView: CardView = accountScene.findViewById(R.id.account_cardFour)

    init {
        accountCardView.setOnClickListener {
            val intent = Intent(accountScene, AccountInfoScene::class.java)
            intent.putExtra("position", 0)
            accountScene.finish()
            accountScene.startActivity(intent)
        }
        favoriteCardView.setOnClickListener {
            val intent = Intent(accountScene, AccountInfoScene::class.java)
            intent.putExtra("position", 1)
            accountScene.finish()
            accountScene.startActivity(intent)
        }
        watchlistCardView.setOnClickListener {
            val intent = Intent(accountScene, AccountInfoScene::class.java)
            intent.putExtra("position", 2)
            accountScene.finish()
            accountScene.startActivity(intent)
        }
        mylistsCardView.setOnClickListener {
            val intent = Intent(accountScene, AccountInfoScene::class.java)
            intent.putExtra("position", 3)
            accountScene.finish()
            accountScene.startActivity(intent)
        }
    }
}