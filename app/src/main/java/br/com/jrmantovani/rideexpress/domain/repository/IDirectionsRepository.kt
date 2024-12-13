package br.com.jrmantovani.rideexpress.domain.repository

import com.google.android.gms.maps.model.LatLng

interface IDirectionsRepository {
    suspend fun getRoute(origin: String, destination: String, apiKey: String): List<LatLng>
}