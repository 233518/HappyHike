package com.example.filmatory.systems

import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.scenes.SuperScene

/**
 * FavoriteSystem takes care of favorite actions
 *
 * @property superScene The scene to use
 * @property movieSystem The movie system to use
 * @property tvSystem The tv system to use
 */
class FavoriteSystem(private var superScene: SuperScene, private var movieSystem: MovieSystem?, private var tvSystem: TvSystem?) {

    /**
     * Adds a movie to the users favorite
     *
     * @param mId The movie ID
     * @return Boolean
     */
    fun addMovieToFavorites(mId: String) : Boolean {
        superScene.runOnUiThread {
            movieSystem?.addMovieToFavorites(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }

    /**
     * Removes a movie from the users favorite
     *
     * @param mId The movie ID
     * @return Boolean
     */
    fun removeMovieFromFavorites(mId: String) : Boolean {
        superScene.runOnUiThread {
            movieSystem?.removeMovieFromFavorites(superScene.auth.currentUser!!.uid, mId)
        }
        return true
    }

    /**
     * Checks if movie is favorited
     *
     * @param favorites The users favorites
     * @param mId The movie ID
     * @return Boolean
     */
    fun checkIfMovieFavorited(favorites : Favorites, mId: Int) : Boolean {
        for (item in favorites.userMovieFavorites) {
            if (item.id == mId) return true
        }
        return false
    }

    /**
     * Adds a tv to the users favorites
     *
     * @param tId The tv ID
     * @return Boolean
     */
    fun addTvToFavorites(tId: String) : Boolean {
        superScene.runOnUiThread {
            tvSystem?.addTvToFavorites(superScene.auth.currentUser!!.uid, tId)
        }
        return true
    }

    /**
     * Removes a tv from the users favorites
     *
     * @param tId The tv ID
     * @return Boolean
     */
    fun removeTvFromFavorites(tId: String) : Boolean {
        superScene.runOnUiThread {
            tvSystem?.removeTvFromFavorites(superScene.auth.currentUser!!.uid, tId)
        }
        return true
    }

    /**
     * Checks if tv is favorited
     *
     * @param favorites The users favorites
     * @param tId The tv ID
     * @return Boolean
     */
    fun checkIfTvFavorited(favorites : Favorites, tId: Int) : Boolean {
        for (item in favorites.userTvFavorites) {
            if (item.id == tId) return true
        }
        return false
    }
}