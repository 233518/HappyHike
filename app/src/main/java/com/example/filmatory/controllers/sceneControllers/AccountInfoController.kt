package com.example.filmatory.controllers.sceneControllers

import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.AccountInfoGui
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.ViewPageAdapter

/**
 * AccountInfoController manipulates the AccountInfoScene gui
 *
 * @property accountInfoScene The AccountInfoScene to use
 */
class AccountInfoController(private val accountInfoScene: AccountInfoScene) : MainController(accountInfoScene) {
    private val accountInfoGui = AccountInfoGui(accountInfoScene, this)
    private var tabAdapter = ViewPageAdapter(accountInfoScene.supportFragmentManager, accountInfoScene.lifecycle, accountInfoScene, apiSystem)

    init {
        initializeTabAdapter()
        apiSystem.requestUserFavorites(RequestBaseOptions(null, accountInfoScene.auth.currentUser?.uid, ::getUserFavorites, ::onFailure))
        apiSystem.requestUserWatchlist(RequestBaseOptions(null, accountInfoScene.auth.currentUser?.uid, ::getUserWatchlist, ::onFailure))
        apiSystem.requestUserLists(RequestBaseOptions(null, accountInfoScene.auth.currentUser?.uid, ::getUserLists, ::onFailure), languageCode)
    }

    private fun getUserFavorites(favorites: Favorites){
        tabAdapter.favoriteFragment.showFavorites(favorites)
        tabAdapter.statisticsFragment.statisticsFavorites(favorites)
    }

    private fun getUserWatchlist(watchlist: Watchlist){
        tabAdapter.watchlistFragment.showWatchlist(watchlist)
        tabAdapter.statisticsFragment.statisticsWatchlist(watchlist)
    }

    private fun getUserLists(userLists: UserLists){
        tabAdapter.listFragment.showUserLists(userLists)
    }

    private fun initializeTabAdapter(){
        val defaultPage = 0
        val page = accountInfoScene.intent.getIntExtra("position", defaultPage)

        accountInfoGui.viewPager2.offscreenPageLimit = 5
        accountInfoGui.viewPager2.adapter = tabAdapter

        accountInfoGui.viewPager2.currentItem = page
        accountInfoGui.initializeTab()
    }
}