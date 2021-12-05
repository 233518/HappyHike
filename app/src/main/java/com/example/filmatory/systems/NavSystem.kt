package com.example.filmatory.systems

import android.content.Intent
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.example.filmatory.R
import com.example.filmatory.scenes.activities.*
import com.example.filmatory.scenes.activities.auth.LoginScene
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yariksoffice.lingver.Lingver

/**
 * NavSystem is used to initialize the navigation in the application
 *
 * @constructor
 *
 * @param appCompatActivity The activity the navigation is on
 */
class NavSystem(appCompatActivity: AppCompatActivity)  {
    private var toggle: ActionBarDrawerToggle
    private var drawerLayout: DrawerLayout = appCompatActivity.findViewById(R.id.drawer_layout)
    private var navigationView: NavigationView = appCompatActivity.findViewById(R.id.nav_view)
    private var toolbar: Toolbar = appCompatActivity.findViewById(R.id.main_toolbar)
    private var langBtn : ImageButton = appCompatActivity.findViewById(R.id.language_btn)
    init{
        appCompatActivity.setSupportActionBar(toolbar)
        appCompatActivity.supportActionBar?.setDisplayShowHomeEnabled(true)
        toggle = ActionBarDrawerToggle(
            appCompatActivity,
        drawerLayout,
        toolbar,
        R.string.openNavDrawer,
        R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        langBtn.setOnClickListener{
            changeLangAlert(appCompatActivity)
        }
        currentLang(appCompatActivity)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_movies -> {
                    val intent = Intent(appCompatActivity, MoviesScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_series -> {
                    val intent = Intent(appCompatActivity, TvsScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_upcoming_movies -> {
                    val intent = Intent(appCompatActivity, UpcomingMoviesScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_upcoming_tvshows -> {
                    val intent = Intent(appCompatActivity, UpcomingTvsScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_lists -> {
                    val intent = Intent(appCompatActivity, ListsScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_profile -> {
                    val intent = Intent(appCompatActivity, AccountScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_favorites -> {
                    val intent = Intent(appCompatActivity, AccountInfoScene::class.java)
                    intent.putExtra("position", 1)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_watchlist -> {
                    val intent = Intent(appCompatActivity, AccountInfoScene::class.java)
                    intent.putExtra("position", 2)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_my_lists -> {
                    val intent = Intent(appCompatActivity, AccountInfoScene::class.java)
                    intent.putExtra("position", 3)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_logout -> {
                    Firebase.auth.signOut()
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                R.id.nav_user_login -> {
                    val intent = Intent(appCompatActivity, LoginScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
            }
            true
        }
    }
    fun changeLangAlert(appCompatActivity: AppCompatActivity){
        var dialog : AlertDialog
        var availableLangs = arrayOf("English", "Norsk", "Deutsch", "Français", "Español", "中国人")
        var builder = AlertDialog.Builder(appCompatActivity)
        builder.setTitle(R.string.choose_lang)
        builder.setSingleChoiceItems(availableLangs,-1) { _, which ->
            when(which){
                0 -> {
                    Lingver.getInstance().setLocale(appCompatActivity, "en")
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                1 -> {
                    Lingver.getInstance().setLocale(appCompatActivity, "no")
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                2 -> {
                    Lingver.getInstance().setLocale(appCompatActivity, "de")
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                3 -> {
                    Lingver.getInstance().setLocale(appCompatActivity, "fr")
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                4 -> {
                    Lingver.getInstance().setLocale(appCompatActivity, "es")
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                5 -> {
                    Lingver.getInstance().setLocale(appCompatActivity, "zh")
                    val intent = Intent(appCompatActivity, StartScene::class.java)
                    appCompatActivity.finish()
                    appCompatActivity.startActivity(intent)
                }
                else -> {
                    println("Error her")
                }
            }
        }
        dialog = builder.create()
        dialog.show()
    }
    fun currentLang(appCompatActivity: AppCompatActivity){
        val img : String = Lingver.getInstance().getLocale().toString()
        langBtn.setImageResource(appCompatActivity.resources.getIdentifier(img, "drawable", appCompatActivity.packageName))
    }
}
