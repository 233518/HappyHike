package com.example.filmatory.utils.observers

import com.anychart.charts.Pie

/**
 * This interface is used to create observables on account info components
 *
 */
interface AccountInfoObservables {
    /**
     * Registers an observer
     *
     * @param accountInfoObserver The observer to register
     */
    fun registerObserver(accountInfoObserver: AccountInfoObserver)

    /**
     * Removes an observer
     *
     * @param accountInfoObserver The observer to remove
     */
    fun removeObserver(accountInfoObserver: AccountInfoObserver)

    /**
     * Notifies the observers that statistics have changed
     *
     * @param pie The pie to update
     */
    fun notifyStatisticsObservers(pie: Pie)
}