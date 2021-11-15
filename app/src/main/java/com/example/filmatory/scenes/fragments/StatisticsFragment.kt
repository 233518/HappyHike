package com.example.filmatory.scenes.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.listener.ListenersInterface
import com.anychart.charts.Pie
import com.example.filmatory.R
import com.example.filmatory.api.data.user.Watchlist
import com.example.filmatory.utils.adapters.DataAdapter
import com.example.filmatory.utils.items.MediaModel
import android.widget.Toast
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.chart.common.listener.Event
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.example.filmatory.api.data.user.Favorites


class StatisticsFragment : Fragment(R.layout.fragment_statistics) {
    private var totalFavoriteMovies : Int = 0
    private var totalFavoriteTvs : Int = 0
    private var totalWatchlistTvs : Int = 0
    private var totalWatchlistMovies : Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val anyChartView : AnyChartView = view.findViewById(R.id.chart_view)
        val pie : Pie = AnyChart.pie()

        pie.setOnClickListener(object : ListenersInterface.OnClickListener(arrayOf("x", "value")) {
            override fun onClick(event: Event) {
                Toast.makeText(requireContext(), event.data["x"].toString() + ":" + event.data["value"], Toast.LENGTH_SHORT).show()
            }
        })

        val data : MutableList<DataEntry> = ArrayList()
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

    fun statisticsFavorites(favorites: Favorites){
        for(item in favorites.userAllFavorites){
            if(item.type == "tv"){
                totalFavoriteTvs++
            } else {
                totalFavoriteMovies++
            }
        }
    }

    fun statisticsWatchlist(watchlist: Watchlist){
        for(item in watchlist.userAllWatched){
            if(item.type == "tv"){
                totalWatchlistTvs++
            } else {
                totalWatchlistMovies++
            }
        }
    }
}