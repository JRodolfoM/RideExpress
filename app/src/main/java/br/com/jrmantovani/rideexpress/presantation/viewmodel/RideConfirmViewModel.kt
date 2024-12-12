package br.com.jrmantovani.rideexpress.presantation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.data.remote.dto.RideConfirmResponse
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest

import br.com.jrmantovani.rideexpress.domain.usecase.RideConfirmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideConfirmViewModel @Inject constructor(
    private val rideConfirmUseCase: RideConfirmUseCase
): ViewModel() {

    fun rideConfirm(
        rideConfirmRequest: RideConfirmRequest,
        uiStatus: (UIStatus<RideConfirmResponse>)->Unit
    ){
        viewModelScope.launch {
            rideConfirmUseCase.invoke(rideConfirmRequest, uiStatus)
        }
    }
}