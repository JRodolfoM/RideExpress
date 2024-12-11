package br.com.jrmantovani.rideexpress.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Motorist(
    val description: String,
    val id: Int,
    val name: String,
    val rating:Int,
    val value: Double,
    val vehicle: String
): Parcelable
