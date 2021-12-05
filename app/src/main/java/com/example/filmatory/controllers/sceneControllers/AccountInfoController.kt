package com.example.filmatory.controllers.sceneControllers

import com.anychart.charts.Pie
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.AccountInfoGui
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.ViewPageAdapter
import com.example.filmatory.utils.observers.AccountInfoObserver

/**
 * AccountInfoController manipulates the AccountInfoScene gui
 *
 * @property accountInfoScene The AccountInfoScene to use
 */
class AccountInfoController(private val accountInfoScene: AccountInfoScene) : MainController(accountInfoScene), AccountInfoObserver{
    private val accountInfoGui = AccountInfoGui(accountInfoScene, this)
    private var tabAdapter = ViewPageAdapter(accountInfoScene.supportFragmentManager, accountInfoScene.lifecycle, accountInfoScene, apiSystem)
    private lateinit var favorites: Favorites
    private lateinit var watchlist: Watchlist
    private var ready = 0

    init {
        initializeTabAdapter()
        apiSystem.requestUserFavorites(RequestBaseOptions(null, uid, ::getUserFavorites, ::onFailure))
        apiSystem.requestUserWatchlist(RequestBaseOptions(null, uid, ::getUserWatchlist, ::onFailure))
        apiSystem.requestUserLists(RequestBaseOptions(null, uid, ::getUserLists, ::onFailure), languageCode)
        tabAdapter.statisticsFragment.registerObserver(this)
    }

    private fun getUserFavorites(favorites: Favorites){
        tabAdapter.favoriteFragment.showFavorites(favorites)
        this.favorites = favorites
        ready++
    }

    private fun getUserWatchlist(watchlist: Watchlist){
        tabAdapter.watchlistFragment.showWatchlist(watchlist)
        this.watchlist = watchlist
        ready++
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
    override fun onStatisticsInitialized(pie: Pie) {
        if(ready == 2) {
            tabAdapter.statisticsFragment.updateGraph(favorites, watchlist, pie)
        } else {
            while(ready != 2) {
                Thread.sleep(500)
            }
            tabAdapter.statisticsFragment.updateGraph(favorites, watchlist, pie)
        }
    }
}