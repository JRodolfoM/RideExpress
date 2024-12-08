package br.com.jrmantovani.rideexpress.data.remote.model

import com.google.gson.annotations.SerializedName

data class RideEstimateRequest(
    @SerializedName("customer_id")
    val customerId: String,
    val origin: String,
    val destination: String
)
