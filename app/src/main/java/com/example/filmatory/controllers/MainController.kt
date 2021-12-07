package com.example.filmatory.controllers

import android.content.Intent
import com.example.filmatory.R
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.scenes.activities.AccountScene
import com.example.filmatory.systems.*
import com.yariksoffice.lingver.Lingver

/**
 * MainController manipulates the scene gui
 * Every controller will extend this class
 *
 * @constructor
 *
 * @param scene The scene the controller will be connected to
 */
open class MainController(protected val scene : SuperScene) {
    val navSystem = NavSystem(scene)
    val apiSystem = ApiSystem()
    val snackbarSystem = SnackbarSystem(scene.findViewById(R.id.snackbar_layout))
    val languageCode: String = Lingver.getInstance().getLanguage()

    val uid = scene.auth.currentUser?.uid
    val isLoggedIn : Boolean = uid != null

    fun onFailure(error : BaseError) {
        snackbarSystem.showSnackbarFailure(error.message, ::redirectHome, "Home")
    }
    fun redirectHome() {
        val intent = Intent(scene, AccountScene::class.java)
        scene.finish()
        scene.startActivity(intent)
    }
    open fun getFavoriteSystem() : FavoriteSystem? {
        return null
    }

    open fun getWatchlistSystem() : WatchlistSystem? {
        return null
    }
}