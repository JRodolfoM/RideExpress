package br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate

data class DestinationX(
    val geocoderStatus: GeocoderStatus,
    val placeId: String,
    val type: List<String>
)