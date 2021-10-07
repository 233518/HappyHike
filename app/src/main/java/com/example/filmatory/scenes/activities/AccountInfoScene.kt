package com.example.filmatory.scenes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.viewpager2.widget.ViewPager2
import com.example.filmatory.R
import com.example.filmatory.controllers.AccountInfoController
import com.example.filmatory.utils.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AccountInfoScene : SuperScene() {
    private lateinit var accountInfoController: AccountInfoController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_information)
        accountInfoController = AccountInfoController(this)

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)
        val viewPager2: ViewPager2 = findViewById(R.id.tab_viewpager)
        val tabAdapter = ViewPageAdapter(supportFragmentManager, lifecycle)

        viewPager2.adapter = tabAdapter

        val defaultPage = 0
        val page = intent.getIntExtra("position", defaultPage)
        viewPager2.setCurrentItem(page)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = getString(R.string.acc_info)
                }
                1 -> {
                    tab.text = getString(R.string.favorites)
                }
                2 -> {
                    tab.text = getString(R.string.watchlist)
                }
                3 -> {
                    tab.text = getString(R.string.mylists)
                }
            }
        }.attach()
    }
}
