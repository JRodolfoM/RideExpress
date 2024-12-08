package br.com.jrmantovani.rideexpress.presantation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.usecase.GetRideEstimateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RideEstimateViewModel @Inject constructor(
    private val getRideEstimateUseCase: GetRideEstimateUseCase
):ViewModel() {


    fun getRideEstimate(rideEstimateRequest: RideEstimateRequest) {
       viewModelScope.launch {
         val retorno=  getRideEstimateUseCase.getRideEstimate(rideEstimateRequest)


           Log.i("info_teste", "getRideEstimate: $retorno")


       }

    }

}