package br.com.jrmantovani.rideexpress.domain.usecase

import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate
import br.com.jrmantovani.rideexpress.domain.repository.RideRepository
import javax.inject.Inject

class GetRideEstimateUseCase @Inject constructor(
    private val rideRepository: RideRepository
){
    suspend fun getRideEstimate(rideEstimateRequest: RideEstimateRequest): RideEstimate {
//        return try {
//            rideRepository.getRideEstimate(rideEstimateRequest)
//        }catch (e:Exception){
//            e.printStackTrace()
//
//        }
        return rideRepository.getRideEstimate(rideEstimateRequest)

    }

}