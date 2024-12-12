package br.com.jrmantovani.rideexpress.domain.usecase

import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.data.remote.dto.RideConfirmResponse
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest
import br.com.jrmantovani.rideexpress.domain.repository.IRideRepository
import javax.inject.Inject

class RideConfirmUseCase@Inject constructor(
    private val rideRepositoryImpl: IRideRepository
) {

    suspend operator fun invoke(
        rideConfirmRequest: RideConfirmRequest,
        uiStatus: (UIStatus<RideConfirmResponse>)->Unit
    ){
        uiStatus.invoke( UIStatus.Loading )
        rideRepositoryImpl.rideConfirm(rideConfirmRequest ){status->
            when(status){
                is UIStatus.Success-> uiStatus.invoke(UIStatus.Success(status.data))
                is UIStatus.Loading -> uiStatus.invoke(UIStatus.Loading)
                is UIStatus.Error -> uiStatus.invoke(UIStatus.Error(status.errorMessage))
            }

        }


    }

}