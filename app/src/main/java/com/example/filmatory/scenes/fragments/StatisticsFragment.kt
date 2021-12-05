package com.example.filmatory.scenes.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.charts.Pie
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Watchlist
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.utils.observers.AccountInfoObservables
import com.example.filmatory.utils.observers.AccountInfoObserver
import java.util.ArrayList

class StatisticsFragment : Fragment(R.layout.fragment_statistics), AccountInfoObservables {
    private var totalFavoriteMovies : Int = 0
    private var totalFavoriteTvs : Int = 0
    private var totalWatchlistTvs : Int = 0
    private var totalWatchlistMovies : Int = 0
    private lateinit var anyChartView : AnyChartView
    private lateinit var pie: Pie
    private var data : MutableList<DataEntry> = ArrayList()
    private val mObservers: ArrayList<AccountInfoObserver> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        anyChartView = view.findViewById(R.id.chart_view)
        pie = AnyChart.pie()
        notifyObservers()
    }

    //https://github.com/AnyChart/AnyChart-Android/issues/145
    fun updateGraph(favorites: Favorites, watchlist: Watchlist) {
        for(item in favorites.userAllFavorites){
            if(item.type == "tv"){
                totalFavoriteTvs++
            } else {
                totalFavoriteMovies++
            }
        }
        for(item in watchlist.userAllWatched){
            if(item.type == "tv"){
                totalWatchlistTvs++
            } else {
                totalWatchlistMovies++
            }
        }

        data.add(ValueDataEntry("Favorite Tv-shows", totalFavoriteTvs))
        data.add(ValueDataEntry("Favorite Movies", totalFavoriteMovies))
        data.add(ValueDataEntry("Watchlist Tv-shows", totalWatchlistTvs))
        data.add(ValueDataEntry("Watchlist Movies", totalWatchlistMovies))

        pie.data(data)
        pie.title(getString(R.string.favoriteandwatchlist))

        pie.labels().position("outside")
        pie.legend().title().enabled(true)
        pie.legend().title()
            .text(getString(R.string.mediatype))
            .padding(0, 0, 10, 0)
        pie.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL)
            .align(Align.CENTER)
        anyChartView.setChart(pie)
    }

    override fun registerObserver(accountInfoObserver: AccountInfoObserver) {
        if(!mObservers.contains(accountInfoObserver)) {
            mObservers.add(accountInfoObserver);
        }
    }

    override fun removeObserver(accountInfoObserver: AccountInfoObserver) {
        if(mObservers.contains(accountInfoObserver)) {
            mObservers.remove(accountInfoObserver);
        }
    }

    override fun notifyObservers() {
        for (observer in mObservers) {
            observer.onStatisticsInitialized()
        }
    }
}