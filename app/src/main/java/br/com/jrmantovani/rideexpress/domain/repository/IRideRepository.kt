package br.com.jrmantovani.rideexpress.domain.repository

import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.data.remote.dto.RideConfirmResponse
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate
import br.com.jrmantovani.rideexpress.domain.model.RideHistory

interface IRideRepository {
    suspend fun getRideEstimate(
        rideEstimateRequest: RideEstimateRequest,
        uiStatus: (UIStatus<RideEstimate>)->Unit
    )

    suspend fun rideConfirm(
        rideConfirmRequest: RideConfirmRequest,
        uiStatus: (UIStatus<RideConfirmResponse>)->Unit
    )

    suspend fun getRideHistory(
        customerId: String,
        driverId: String,
        uiStatus: (UIStatus<List<RideHistory>>)->Unit
    )

}