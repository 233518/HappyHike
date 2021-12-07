package com.example.filmatory.utils.observers

import com.anychart.charts.Pie

/**
 * This interface is used to create observers for account info components
 *
 */
interface AccountInfoObserver {
    /**
     * Will run when statistics has been initialized successfully
     *
     * @param pie The pie chart to update
     */
    fun onStatisticsInitialized(pie: Pie)
}