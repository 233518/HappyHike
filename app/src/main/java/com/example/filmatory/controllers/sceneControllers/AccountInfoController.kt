package com.example.filmatory.controllers.sceneControllers

import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.scenes.activities.AccountInfoScene


/**
 * AccountInfoController manipulates the AccountInfoScene gui
 *
 * @property accountInfoScene The AccountInfoScene to use
 */
class AccountInfoController(private val accountInfoScene: AccountInfoScene) : MainController(accountInfoScene) {
    init {
        accountInfoScene.auth.currentUser?.let { apiSystem.requestUserFavorites(it.uid, ::getUserFavorites) }
        accountInfoScene.auth.currentUser?.let { apiSystem.requestUserWatchlist(it.uid, ::getUserWatchlist) }
        //accountInfoScene.auth.currentUser?.let { apiSystem.requestUserLists(it.uid, ::getUserLists) }

    }

    private fun getUserFavorites(favorites: Favorites){
        accountInfoScene.runOnUiThread(Runnable{
            accountInfoScene.tabAdapter.favoriteFragment.showFavorites(favorites)
        })
    }

    private fun getUserWatchlist(watchlist: Watchlist){
        accountInfoScene.runOnUiThread(Runnable{
            accountInfoScene.tabAdapter.watchlistFragment.showWatchlist(watchlist)
        })
    }

    private fun getUserLists(userLists: UserLists){
        accountInfoScene.runOnUiThread(Runnable{
            accountInfoScene.tabAdapter.listFragment.showUserLists(userLists)
        })
    }
}