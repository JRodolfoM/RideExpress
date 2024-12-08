package br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate

import br.com.jrmantovani.rideexpress.domain.model.Motorist

data class Option(
    val description: String,
    val id: Int,
    val name: String,
    val review: Review,
    val value: Double,
    val vehicle: String
)
    fun Option.toMotorist(): Motorist {
        return Motorist(
            description = this.description,
            id = this.id,
            name = this.name,
            rating = this.review.rating,
            value = this.value,
            vehicle = this.vehicle
        )
    }




