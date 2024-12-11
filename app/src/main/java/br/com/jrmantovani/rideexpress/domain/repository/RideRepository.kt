package br.com.jrmantovani.rideexpress.domain.repository

import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate

interface IRideRepository {
    suspend fun getRideEstimate(
        rideEstimateRequest: RideEstimateRequest,
        uiStatus: (UIStatus<RideEstimate>)->Unit
    )

}