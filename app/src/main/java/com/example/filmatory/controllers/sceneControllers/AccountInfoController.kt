package com.example.filmatory.controllers.sceneControllers

import com.anychart.charts.Pie
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.guis.AccountInfoGui
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.systems.FavoriteSystem
import com.example.filmatory.systems.MovieSystem
import com.example.filmatory.systems.TvSystem
import com.example.filmatory.systems.WatchlistSystem
import com.example.filmatory.utils.adapters.ViewPageAdapter
import com.example.filmatory.utils.observers.AccountInfoObserver

/**
 * AccountInfoController controls everything related to the account info page
 * Implements AccountInfoObserver for updating components
 *
 * @property accountInfoScene The AccountInfoScene to use
 */
class AccountInfoController(private val accountInfoScene: AccountInfoScene) : MainController(accountInfoScene), AccountInfoObserver {
    private val accountInfoGui = AccountInfoGui(accountInfoScene, this)
    private var tabAdapter = ViewPageAdapter(accountInfoScene.supportFragmentManager, accountInfoScene.lifecycle, accountInfoScene, this)
    private val movieSystem = MovieSystem(apiSystem, snackbarSystem, accountInfoScene)
    private val tvSystem = TvSystem(apiSystem, snackbarSystem, accountInfoScene)
    private val favoriteSystem = FavoriteSystem(accountInfoScene, movieSystem, tvSystem)
    private val watchlistSystem = WatchlistSystem(accountInfoScene, movieSystem, tvSystem)
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

    /**
     * Runs a method to remove a movie from watchlist adapter and updates the view
     *
     * @param position : The position of the element
     */
    override fun notifyMovieWatchlistAdapter(position: Int) {
        tabAdapter.watchlistFragment.removeMovieItem(position)
    }

    /**
     * Runs a method to remove a tv from watchlist adapter and updates the view
     *
     * @param position : The position of the element
     */
    override fun notifyTvWatchlistAdapter(position: Int) {
        tabAdapter.watchlistFragment.removeTvItem(position)
    }

    /**
     * Runs a method to remove a movie from favorites adapter and updates the view
     *
     * @param position : The position of the element
     */
    override fun notifyMovieFavoriteAdapter(position: Int) {
        tabAdapter.favoriteFragment.removeMovieItem(position)
    }

    /**
     * Runs a method to remove a tv from favorites adapter and updates the view
     *
     * @param position : The position of the element
     */
    override fun notifyTvFavoriteAdapter(position: Int) {
        tabAdapter.favoriteFragment.removeTvItem(position)
    }

    /**
     * Runs a methods to retrieve user favorites
     *
     * @param favorites : Object of user favorites
     */
    private fun getUserFavorites(favorites: Favorites){
        tabAdapter.favoriteFragment.showFavorites(favorites)
        this.favorites = favorites
        ready++
    }

    /**
     * Runs a methods to retrieve user watchlist
     *
     * @param watchlist : Object of user watchlist
     */
    private fun getUserWatchlist(watchlist: Watchlist){
        tabAdapter.watchlistFragment.showWatchlist(watchlist)
        this.watchlist = watchlist
        ready++
    }

    /**
     * Runs a method to retrive user lists
     *
     * @param userLists : Object of user lists
     */
    private fun getUserLists(userLists: UserLists){
        tabAdapter.listFragment.showUserLists(userLists)
    }

    /**
     * Initlizes the tab adapter for tablayout in accountscene
     *
     */
    private fun initializeTabAdapter(){
        val defaultPage = 0
        val page = accountInfoScene.intent.getIntExtra("position", defaultPage)

        accountInfoGui.viewPager2.offscreenPageLimit = 5
        accountInfoGui.viewPager2.adapter = tabAdapter

        accountInfoGui.viewPager2.currentItem = page
        accountInfoGui.initializeTab()
    }

    /**
     * Updates the piechart statistics when favorite and watchlist data has loaded
     *
     * @param pie : Piechart that you want to update
     */
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

    /**
     * Gets the system that contains methods for favorites
     *
     * @return favoriteSystem
     */
    override fun getFavoriteSystem(): FavoriteSystem {
        return favoriteSystem
    }

    /**
     * Gets the system that contains methods for watchlists
     *
     * @return watchlistSystem
     */
    override fun getWatchlistSystem(): WatchlistSystem {
        return watchlistSystem
    }
}