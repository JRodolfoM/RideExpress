package br.com.jrmantovani.rideexpress.data.remote.api


import br.com.jrmantovani.rideexpress.data.remote.dto.RideConfirmResponse
import br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate.RideEstimateDTO
import br.com.jrmantovani.rideexpress.data.remote.dto.ridehistory.RideHistoryDTO
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface RideAPI {
    @POST("ride/estimate")
    suspend fun rideEstimate(@Body rideEstimateRequest: RideEstimateRequest): Response<RideEstimateDTO>

    @PATCH("ride/confirm")
    suspend fun rideConfirm(@Body rideConfirmRequest: RideConfirmRequest): Response<RideConfirmResponse>

    @GET("ride/{customer_id}")
    suspend fun getRideHistory(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: String?
    ): Response<RideHistoryDTO>

    @GET("ride/{customer_id}")
    suspend fun getRideHistory(
        @Path("customer_id") customerId: String,
    ): Response<RideHistoryDTO>

}