package br.com.jrmantovani.rideexpress.data.repository

import br.com.jrmantovani.rideexpress.data.remote.api.DirectionsApi
import br.com.jrmantovani.rideexpress.domain.repository.IDirectionsRepository
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import javax.inject.Inject

class DirectionsRepositoryImpl @Inject constructor(
    private val apiService: DirectionsApi
):IDirectionsRepository {
    override
    suspend fun getRoute(origin: String, destination: String, apiKey: String): List<LatLng> {
        val response = apiService.getDirections(origin, destination, apiKey)
        if (response.isSuccessful) {
            val directions = response.body()
            val polyline = directions?.routes?.firstOrNull()?.overview_polyline?.points
            return if (!polyline.isNullOrEmpty()) {
                PolyUtil.decode(polyline)
            } else {
                emptyList()
            }
        } else {
            throw Exception("Falha ao buscar a rota")
        }
    }
}