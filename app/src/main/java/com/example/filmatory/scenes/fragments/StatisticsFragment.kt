package com.example.filmatory.scenes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.charts.Pie
import com.example.filmatory.api.data.user.Watchlist
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Favorites
import com.example.filmatory.utils.observers.AccountInfoObservables
import com.example.filmatory.utils.observers.AccountInfoObserver
import java.util.ArrayList

/**
 * This fragments is a component to show the users statistics in a pie chart
 *
 */
class StatisticsFragment : Fragment(R.layout.fragment_statistics), AccountInfoObservables {
    private var totalFavoriteMovies : Int = 0
    private var totalFavoriteTvs : Int = 0
    private var totalWatchlistTvs : Int = 0
    private var totalWatchlistMovies : Int = 0
    private var data : MutableList<DataEntry> = ArrayList()
    private var mObservers: ArrayList<AccountInfoObserver> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var pie = AnyChart.pie()
        var anyChartView : AnyChartView = view.findViewById(R.id.chart_view)

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
        notifyObservers(pie)
    }

    //https://github.com/AnyChart/AnyChart-Android/issues/145
    fun updateGraph(favorites: Favorites, watchlist: Watchlist, pie: Pie) {
        data.clear()
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
        data.add(ValueDataEntry(requireActivity().resources.getString(R.string.favorite_tvs), totalFavoriteTvs))
        data.add(ValueDataEntry(requireActivity().resources.getString(R.string.favorite_movies), totalFavoriteMovies))
        data.add(ValueDataEntry(requireActivity().resources.getString(R.string.watchlist_tvs), totalWatchlistTvs))
        data.add(ValueDataEntry(requireActivity().resources.getString(R.string.watchlist_movies), totalWatchlistMovies))
        pie.data(data)
    }

    override fun registerObserver(accountInfoObserver: AccountInfoObserver) {
        if(!mObservers.contains(accountInfoObserver)) {
            mObservers.add(accountInfoObserver)
        }
    }

    override fun removeObserver(accountInfoObserver: AccountInfoObserver) {
        if(mObservers.contains(accountInfoObserver)) {
            mObservers.remove(accountInfoObserver)
        }
    }

    override fun notifyObservers(pie: Pie) {
        for (observer in mObservers) {
            observer.onStatisticsInitialized(pie)
        }
    }
}