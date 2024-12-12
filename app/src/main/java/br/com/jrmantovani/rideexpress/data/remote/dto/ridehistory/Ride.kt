package br.com.jrmantovani.rideexpress.data.remote.dto.ridehistory

data class Ride(
    val date: String,
    val destination: String,
    val distance: Double,
    val driver: Driver,
    val duration: String,
    val id: Int,
    val origin: String,
    val value: Double
)