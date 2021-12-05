package com.example.filmatory.utils.observers

interface AccountInfoObservables {
    fun registerObserver(repositoryObserver: AccountInfoObserver)
    fun removeObserver(repositoryObserver: AccountInfoObserver)
    fun notifyObservers()
}