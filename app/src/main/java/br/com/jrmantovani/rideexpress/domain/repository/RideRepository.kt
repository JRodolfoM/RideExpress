package br.com.jrmantovani.rideexpress.domain.repository

import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate

interface RideRepository {
    suspend fun getRideEstimate(rideEstimateRequest: RideEstimateRequest): RideEstimate

}