package com.example.filmatory.scenes.activities

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.filmatory.R
import com.example.filmatory.controllers.sceneControllers.AccountInfoController
import com.example.filmatory.scenes.SuperScene
import com.example.filmatory.utils.ViewPageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * AccountInfoScene is the scene for showing the account info?
 *
 */
class AccountInfoScene : SuperScene() {
    lateinit var accountInfoController: AccountInfoController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_information)


    }

    override fun onStart() {
        super.onStart()
        accountInfoController = AccountInfoController(this)
    }
}
