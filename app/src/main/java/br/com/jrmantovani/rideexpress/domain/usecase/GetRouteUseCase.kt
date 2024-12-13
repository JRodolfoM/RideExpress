package br.com.jrmantovani.rideexpress.domain.usecase


import br.com.jrmantovani.rideexpress.domain.repository.IDirectionsRepository
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class GetRouteUseCase @Inject constructor(
    private val directionsRepositoryImpl: IDirectionsRepository
) {

    suspend operator fun invoke(origin: String, destination: String, apiKey: String): List<LatLng> {
        return directionsRepositoryImpl.getRoute(origin, destination, apiKey)
    }
}