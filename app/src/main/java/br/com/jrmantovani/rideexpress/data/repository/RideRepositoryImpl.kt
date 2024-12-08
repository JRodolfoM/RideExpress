package br.com.jrmantovani.rideexpress.data.repository

import br.com.jrmantovani.rideexpress.data.remote.api.RideAPI
import br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate.toRideEstimate
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate
import br.com.jrmantovani.rideexpress.domain.repository.RideRepository
import javax.inject.Inject

class RideRepositoryImpl @Inject constructor(
    private val rideApi: RideAPI
) : RideRepository {
    override

    suspend fun getRideEstimate(rideEstimateRequest: RideEstimateRequest): RideEstimate {

        val response = rideApi.rediEstimate(rideEstimateRequest)

        if (response.isSuccessful) {

            val rideEstimateDTO = response.body()

            if (rideEstimateDTO != null) {
                return rideEstimateDTO.toRideEstimate()
            }


        }

        return RideEstimate(
            0.0,
            0.0,
            0.0,
            0.0,
            0,
            0,
            emptyList()
        )

    }

}