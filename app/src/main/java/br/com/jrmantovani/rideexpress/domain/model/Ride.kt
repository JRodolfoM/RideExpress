package br.com.jrmantovani.rideexpress.domain.model

data class Ride(
    val id: Int,
    val date: String,
    val origin: String,
    val destination: String,
    val driverName: String,
    val distance: Double,
    val duration: String,
    val value: Double
)
