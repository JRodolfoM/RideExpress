package br.com.jrmantovani.rideexpress.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RideEstimate(
    val latitudeOrigin: Double,
    val longitudeOrigin: Double,
    val latitudeDestination: Double,
    val longitudeDestination: Double,
    val distance: Int,
    val duration: Int,
    val motorists: List<Motorist>,


    ): Parcelable
