package br.com.jrmantovani.rideexpress.data.remote.api

import br.com.jrmantovani.rideexpress.data.remote.dto.mapdirections.DirectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DirectionsApi {
    @GET("maps/api/directions/json")
    suspend fun getDirections(
        @Query("origin") origin: String,
        @Query("destination") destination: String,
        @Query("key") apiKey: String
    ): Response<DirectionsResponse>
}