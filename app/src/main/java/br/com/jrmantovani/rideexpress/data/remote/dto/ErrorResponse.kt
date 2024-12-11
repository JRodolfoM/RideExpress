package br.com.jrmantovani.rideexpress.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("error_code")
    val errorCode: String,
    @SerializedName("error_description")
    val errorDescription: String
)