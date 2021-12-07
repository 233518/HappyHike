package com.example.filmatory.utils.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmatory.controllers.sceneControllers.AccountInfoController
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.scenes.fragments.*

/**
 * Custom adapter for fragments in accountinfoscene
 *
 * @constructor
 *
 *
 * @param fragmentManager
 * @param lifecycle
 * @param accountInfoScene
 * @param accountInfoController
 */
class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, accountInfoScene: AccountInfoScene, accountInfoController: AccountInfoController) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var favoriteFragment = FavoriteFragment(accountInfoScene, accountInfoController)
    var watchlistFragment = WatchlistFragment(accountInfoScene, accountInfoController)
    var listFragment = ListFragment(accountInfoController.apiSystem, accountInfoScene)
    var statisticsFragment = StatisticsFragment()
    var accinfoFragment = AccinfoFragment(accountInfoScene, accountInfoController)

    /**
     * Total number of pages
     *
     * @return returns total pages
     */
    override fun getItemCount(): Int {
        return 5
    }

    /**
     * Creates fragment in position
     *
     * @param position
     * @return Fragment in position
     */
    override fun createFragment(position: Int): Fragment {
         when(position){
            0 ->{
                return accinfoFragment
            }
            1 ->{
                return favoriteFragment
            }
            2 ->{
                return watchlistFragment
            }
            3 ->{
                return listFragment
            }
            4 -> {
                 return statisticsFragment
             }
            else->{
                return Fragment()
            }
        }
    }
}