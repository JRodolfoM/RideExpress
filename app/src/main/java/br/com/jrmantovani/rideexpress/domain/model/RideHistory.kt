package br.com.jrmantovani.rideexpress.domain.model

data class RideHistory(
    val date: String,
    val driverName: String,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val value: Double,

    )
