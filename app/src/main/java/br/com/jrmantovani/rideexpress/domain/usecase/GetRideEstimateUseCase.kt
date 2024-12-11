package br.com.jrmantovani.rideexpress.domain.usecase

import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate
import br.com.jrmantovani.rideexpress.domain.repository.IRideRepository

import javax.inject.Inject

class GetRideEstimateUseCase @Inject constructor(
    private val rideRepositoryImpl: IRideRepository
){
    suspend operator fun invoke(
    rideEstimateRequest: RideEstimateRequest,
    uiStatus: (UIStatus<RideEstimate>)->Unit
    ){
        uiStatus.invoke( UIStatus.Loading )
        rideRepositoryImpl.getRideEstimate(rideEstimateRequest){status->
            when(status){
                is UIStatus.Success-> uiStatus.invoke(UIStatus.Success(status.data))
                is UIStatus.Loading -> uiStatus.invoke(UIStatus.Loading)
                is UIStatus.Error -> uiStatus.invoke(UIStatus.Error(status.errorMessage))
            }

        }


    }

}