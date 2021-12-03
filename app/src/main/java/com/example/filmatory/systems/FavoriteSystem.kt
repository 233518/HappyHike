package com.example.filmatory.systems

import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.scenes.SuperScene

class FavoriteSystem(private var superScene: SuperScene, private var movieSystem: MovieSystem?, private var tvSystem: TvSystem?) {

    fun addMovieToFavorites(mId: String) : Boolean {
        superScene.runOnUiThread {
            movieSystem?.addMovieToFavorites(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }
    fun removeMovieFromFavorites(mId: String) : Boolean {
        superScene.runOnUiThread {
            movieSystem?.removeMovieFromFavorites(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }
    fun checkIfMovieFavorited(favorites : Favorites, mId: Int) : Boolean {
        for (item in favorites.userMovieFavorites) {
            if (item.id == mId) return true
        }
        return false
    }
    fun addTvToFavorites(mId: String) : Boolean {
        superScene.runOnUiThread {
            tvSystem?.addTvToFavorites(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }
    fun removeTvFromFavorites(mId: String) : Boolean {
        superScene.runOnUiThread {
            tvSystem?.removeTvFromFavorites(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }
    fun checkIfTvFavorited(favorites : Favorites, mId: Int) : Boolean {
        for (item in favorites.userTvFavorites) {
            if (item.id == mId) return true
        }
        return false
    }

}