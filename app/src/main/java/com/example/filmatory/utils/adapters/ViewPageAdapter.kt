package com.example.filmatory.utils.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmatory.scenes.activities.AccountInfoScene
import com.example.filmatory.scenes.fragments.AccinfoFragment
import com.example.filmatory.scenes.fragments.FavoriteFragment
import com.example.filmatory.scenes.fragments.ListFragment
import com.example.filmatory.scenes.fragments.WatchlistFragment
import com.example.filmatory.systems.ApiSystem
import com.google.protobuf.Api


class ViewPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, accountInfoScene: AccountInfoScene, apiSystem: ApiSystem) : FragmentStateAdapter(fragmentManager, lifecycle) {
    var favoriteFragment = FavoriteFragment()
    var watchlistFragment = WatchlistFragment()
    var listFragment = ListFragment()
    var accinfoFragment = AccinfoFragment(apiSystem, accountInfoScene)
    override fun getItemCount(): Int {
        return 4
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
            else->{
                return Fragment()
            }
        }
    }
}