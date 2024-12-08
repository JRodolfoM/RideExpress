package br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate

data class RouteResponse(
    val geocodingResults: GeocodingResults,
    val routes: List<Route>
)