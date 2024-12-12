package br.com.jrmantovani.rideexpress.presantation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.domain.model.RideHistory
import br.com.jrmantovani.rideexpress.domain.usecase.GetRideHistoryUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideHistoryViewModel  @Inject constructor(
private val getRideHistoryUseCase: GetRideHistoryUseCase
): ViewModel() {

    fun getRideHistory(
        customerId: String,
        driverId: String,
        uiStatus: (UIStatus<List<RideHistory>>)->Unit
    ){
        viewModelScope.launch {
            getRideHistoryUseCase.invoke(customerId, driverId, uiStatus)
        }



    }

}