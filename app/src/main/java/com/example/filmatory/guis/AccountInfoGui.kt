package com.example.filmatory.guis

import androidx.viewpager2.widget.ViewPager2
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountInfoController
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * AccountInfoGui contains all the gui elements for the account info page
 *
 * @property accountInfoScene The scene to use
 * @property accountInfoController The controller to use
 */
class AccountInfoGui(private val accountInfoScene: AccountInfoScene, private val accountInfoController: AccountInfoController) {
    var tabLayout: TabLayout = accountInfoScene.findViewById(R.id.tab_layout)
    var viewPager2: ViewPager2 = accountInfoScene.findViewById(R.id.tab_viewpager)

    /**
     * Initializes the main tab
     *
     */
    fun initializeTab() {
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
                4 -> {
                    tab.text = accountInfoScene.getString(R.string.statistics)
                }
            }
        }.attach()
    }
}