package br.com.jrmantovani.rideexpress.presantation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import br.com.jrmantovani.rideexpress.domain.usecase.GetRouteUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getRouteUseCase: GetRouteUseCase
) : ViewModel() {

    val routeLiveData = MutableLiveData<List<LatLng>>()

    fun fetchRoute(origin: String, destination: String, apiKey: String) {
        viewModelScope.launch {
            val route = getRouteUseCase(origin, destination, apiKey)
            routeLiveData.postValue(route)
        }
    }
}