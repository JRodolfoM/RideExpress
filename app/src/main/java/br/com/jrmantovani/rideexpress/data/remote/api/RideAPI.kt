package br.com.jrmantovani.rideexpress.data.remote.api


import br.com.jrmantovani.rideexpress.data.remote.dto.RideConfirmResponse
import br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate.RideEstimateDTO
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface RideAPI {
    @POST("ride/estimate")
    suspend fun rideEstimate(@Body rideEstimateRequest: RideEstimateRequest): Response<RideEstimateDTO>

    @PATCH("ride/confirm")
    suspend fun rideConfirm(@Body rideConfirmRequest: RideConfirmRequest): Response<RideConfirmResponse>


}