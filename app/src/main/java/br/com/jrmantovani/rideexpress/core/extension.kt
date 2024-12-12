package br.com.jrmantovani.rideexpress.core

fun Int.toKm(): Double {
    return this.toDouble() / 1000
}