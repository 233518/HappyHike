package com.example.filmatory.controllers.sceneControllers

import androidx.viewpager2.widget.ViewPager2
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.api.data.user.UserLists
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.controllers.MainController
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.systems.ApiSystem.RequestBaseOptions
import com.example.filmatory.utils.adapters.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * AccountInfoController manipulates the AccountInfoScene gui
 *
 * @property accountInfoScene The AccountInfoScene to use
 */
class AccountInfoController(private val accountInfoScene: AccountInfoScene) : MainController(accountInfoScene) {
    val tabAdapter = ViewPageAdapter(accountInfoScene.supportFragmentManager, accountInfoScene.lifecycle, accountInfoScene, apiSystem)
    init {
        initlizeTabAdapter()
        apiSystem.requestUserFavorites(RequestBaseOptions(null, accountInfoScene.auth.currentUser?.uid, ::getUserFavorites, ::onFailure))
        apiSystem.requestUserWatchlist(RequestBaseOptions(null, accountInfoScene.auth.currentUser?.uid, ::getUserWatchlist, ::onFailure))
        apiSystem.requestUserLists(RequestBaseOptions(null, accountInfoScene.auth.currentUser?.uid, ::getUserLists, ::onFailure))
    }

    fun onFailure(baseError: BaseError) {

    }

    private fun getUserFavorites(favorites: Favorites){
        tabAdapter.favoriteFragment.showFavorites(favorites)
    }

    private fun getUserWatchlist(watchlist: Watchlist){
        tabAdapter.watchlistFragment.showWatchlist(watchlist)
    }

    private fun getUserLists(userLists: UserLists){
        tabAdapter.listFragment.showUserLists(userLists)
    }

    private fun initlizeTabAdapter(){
        val tabLayout: TabLayout = accountInfoScene.findViewById(R.id.tab_layout)
        val viewPager2: ViewPager2 = accountInfoScene.findViewById(R.id.tab_viewpager)
        viewPager2.offscreenPageLimit = 5
        viewPager2.adapter = tabAdapter

        val defaultPage = 0
        val page = accountInfoScene.intent.getIntExtra("position", defaultPage)
        viewPager2.currentItem = page

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = accountInfoScene.getString(R.string.acc_info)
                }
                1 -> {
                    tab.text = accountInfoScene.getString(R.string.favorites)
                }
                2 -> {
                    tab.text = accountInfoScene.getString(R.string.watchlist)
                }
                3 -> {
                    tab.text = accountInfoScene.getString(R.string.mylists)
                }
            }
        }.attach()
    }
}