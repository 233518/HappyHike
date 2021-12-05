package com.example.filmatory.utils.observers

interface AccountInfoObservables {
    fun registerObserver(accountInfoObserver: AccountInfoObserver)
    fun removeObserver(accountInfoObserver: AccountInfoObserver)
    fun notifyObservers()
}