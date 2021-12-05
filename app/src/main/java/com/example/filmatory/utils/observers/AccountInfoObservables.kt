package com.example.filmatory.utils.observers

import com.anychart.charts.Pie

interface AccountInfoObservables {
    fun registerObserver(accountInfoObserver: AccountInfoObserver)
    fun removeObserver(accountInfoObserver: AccountInfoObserver)
    fun notifyObservers(pie: Pie)
}