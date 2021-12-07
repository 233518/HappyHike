package com.example.filmatory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.filmatory.api.data.movie.MovieFrontpage
import com.example.filmatory.api.data.tv.TvFrontpage
import com.example.filmatory.errors.BaseError
import com.example.filmatory.scenes.activities.StartScene
import com.example.filmatory.systems.ApiSystem
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yariksoffice.lingver.Lingver

/**
 * Main activity - This is where everything starts
 */
open class MainActivity : AppCompatActivity() {
    private var ready = 0;

    companion object {
        open var apiSystem = ApiSystem()
        open var auth = Firebase.auth
        open var uid = auth.currentUser?.uid

        open lateinit var languageCode : String
        open lateinit var discoverMovieFrontpage: MovieFrontpage
        open lateinit var discoverTvFrontpage: TvFrontpage
        open lateinit var recMovieFrontpage: MovieFrontpage
        open lateinit var recTvFrontpage: TvFrontpage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Lingver.init(application, "en")
        languageCode = Lingver.getInstance().getLanguage()

        apiSystem.requestMovieFrontpageDiscover(
            ApiSystem.RequestBaseOptions(
                null,
                null,
                ::discoverMoviesData,
                ::onFailure
            ), languageCode)
        apiSystem.requestTvFrontpageDiscover(
            ApiSystem.RequestBaseOptions(
                null,
                null,
                ::discoverTvData,
                ::onFailure
            ), languageCode)
        apiSystem.requestMovieFrontpageRecommend(
            ApiSystem.RequestBaseOptions(
                null,
                uid,
                ::recMovieData,
                ::onFailure
            ), languageCode)
        apiSystem.requestTvFrontpageRecommend(
            ApiSystem.RequestBaseOptions(
                null,
                uid,
                ::recTvData,
                ::onFailure
            ), languageCode)

        setContentView(R.layout.activity_main)

    }

    /**
     * Runs if API fails
     *
     * @param baseError The error
     */
    fun onFailure(baseError: BaseError) {
        //Skulle blitt gjort noe her, men dette rakk vi ikke
        println(baseError.message)
    }

    /**
     * Discover movies response from API
     *
     * @param movieFrontpage The response data
     */
    fun discoverMoviesData(movieFrontpage: MovieFrontpage) {
        discoverMovieFrontpage = movieFrontpage
        ready++
        startApp()
    }

    /**
     * Discover tv response from API
     *
     * @param tvFrontpage The response data
     */
    fun discoverTvData(tvFrontpage: TvFrontpage) {
        discoverTvFrontpage = tvFrontpage
        ready++
        startApp()
    }

    /**
     * Recommended movie response from API
     *
     * @param movieFrontpage The response data
     */
    fun recMovieData(movieFrontpage: MovieFrontpage) {
        recMovieFrontpage = movieFrontpage
        ready++
        startApp()
    }

    /**
     * Recommended tv response from API
     *
     * @param tvFrontpage The response data
     */
    fun recTvData(tvFrontpage: TvFrontpage) {
        recTvFrontpage = tvFrontpage
        ready++
        startApp()
    }

    /**
     * Continues the app when information is fetched
     *
     */
    fun startApp() {
        if(ready == 4) {
            val intent = Intent(this, StartScene::class.java)
            finish()
            startActivity(intent)
        }
    }
}