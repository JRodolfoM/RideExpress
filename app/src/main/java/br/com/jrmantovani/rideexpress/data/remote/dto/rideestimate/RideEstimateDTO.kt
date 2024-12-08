package br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate

import br.com.jrmantovani.rideexpress.domain.model.RideEstimate

data class RideEstimateDTO(
    val destination: Destination,
    val distance: Int,
    val duration: Int,
    val options: List<Option>,
    val origin: Origin,
    val routeResponse: RouteResponse
)

    fun RideEstimateDTO.toRideEstimate():RideEstimate{

        return RideEstimate(
            longitudeDestination = this.destination.longitude,
            latitudeDestination = this.destination.latitude,
            longitudeOrigin = this.origin.longitude,
            latitudeOrigin = this.origin.latitude,
            distance = this.distance,
            duration = this.duration,
            motorists = this.options.map { it.toMotorist() }
        )

    }


