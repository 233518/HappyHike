package com.example.filmatory.utils.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.scenes.fragments.*
import com.example.filmatory.systems.ApiSystem

class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, accountInfoScene: AccountInfoScene, apiSystem: ApiSystem) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var favoriteFragment = FavoriteFragment()
    var watchlistFragment = WatchlistFragment()
    var listFragment = ListFragment(apiSystem, accountInfoScene)
    var statisticsFragment = StatisticsFragment()
    var accinfoFragment = AccinfoFragment(apiSystem, accountInfoScene)
    override fun getItemCount(): Int {
        return 5
    }

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