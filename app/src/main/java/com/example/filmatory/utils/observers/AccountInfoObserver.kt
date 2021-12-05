package com.example.filmatory.utils.observers

import com.anychart.charts.Pie

interface AccountInfoObserver {
    fun onStatisticsInitialized(pie: Pie)
}