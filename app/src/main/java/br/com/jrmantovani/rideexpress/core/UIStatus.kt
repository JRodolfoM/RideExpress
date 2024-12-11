package br.com.jrmantovani.rideexpress.core


sealed class UIStatus<out T> {
    object Loading : UIStatus<Nothing>()
    class Success<T>(val data: T) : UIStatus<T>()
    class Error(val errorMessage: String) : UIStatus<Nothing>()
}