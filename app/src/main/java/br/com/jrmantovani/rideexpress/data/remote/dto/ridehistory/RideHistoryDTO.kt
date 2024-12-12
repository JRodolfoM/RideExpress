package br.com.jrmantovani.rideexpress.data.remote.dto.ridehistory

import br.com.jrmantovani.rideexpress.domain.model.RideHistory
import com.google.gson.annotations.SerializedName

data class RideHistoryDTO(
    @SerializedName("customer_id")
    val customerId: String,
    val rides: List<Ride>
) {
    fun toRideHistory(): List<RideHistory> {
       return rides.map { ride ->
           RideHistory(
               date = ride.date,
               driverName = ride.driver.name,
               origin = ride.origin,
               destination = ride.destination,
               distance = ride.distance,
               duration = ride.duration,
               value = ride.value
           )
       }
    }
}