package br.com.jrmantovani.rideexpress.data.remote.model

import com.google.gson.annotations.SerializedName

data class RideConfirmRequest(
    @SerializedName("customer_id")
    val customerId: String,
    val destination: String,
    val distance: Double,
    val driver: Driver,
    val duration: String,
    val origin: String,
    val value: String
)