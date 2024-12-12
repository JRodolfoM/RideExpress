package br.com.jrmantovani.rideexpress.data.repository


import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.data.remote.api.RideAPI
import br.com.jrmantovani.rideexpress.data.remote.dto.ErrorResponse
import br.com.jrmantovani.rideexpress.data.remote.dto.RideConfirmResponse
import br.com.jrmantovani.rideexpress.data.remote.dto.rideestimate.toRideEstimate
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.domain.model.RideEstimate
import br.com.jrmantovani.rideexpress.domain.repository.IRideRepository
import com.google.gson.Gson

import javax.inject.Inject

class RideRepositoryImpl @Inject constructor(
    private val rideApi: RideAPI
) : IRideRepository {
    override
    suspend fun getRideEstimate(
        rideEstimateRequest: RideEstimateRequest,
        uiStatus: (UIStatus<RideEstimate>) -> Unit
    ) {

        val response = rideApi.rideEstimate(rideEstimateRequest)

        if (response.isSuccessful) {

            val rideEstimateDTO = response.body()


            if (rideEstimateDTO != null) {

                if(rideEstimateDTO.options.isEmpty()){
                    uiStatus.invoke( UIStatus.Error("Nenhum motorista disponível"))
                }else{
                    uiStatus.invoke( UIStatus.Success( rideEstimateDTO.toRideEstimate()))
                }


            }else{
                uiStatus.invoke( UIStatus.Error("Erro ao converter dados"))

            }


        }else{
            val errorBody = response.errorBody()?.string()
            val errorResponse = try {
                Gson().fromJson(errorBody, ErrorResponse::class.java)
            } catch (e: Exception) {
                null
            }

            uiStatus.invoke( UIStatus.Error("${errorResponse?.errorDescription}"))
        }


    }

    override suspend fun rideConfirm(
        rideConfirmRequest: RideConfirmRequest,
        uiStatus: (UIStatus<RideConfirmResponse>) -> Unit,
    ) {
       val response = rideApi.rideConfirm(rideConfirmRequest)
        if (response.isSuccessful) {
            val rideConfirmResponse = response.body()
            if (rideConfirmResponse != null) {
                uiStatus.invoke(UIStatus.Success(rideConfirmResponse))
            } else {
                uiStatus.invoke(UIStatus.Error("Erro ao converter dados"))
            }

        }else{
            val errorBody = response.errorBody()?.string()
            val errorResponse = try {
                Gson().fromJson(errorBody, ErrorResponse::class.java)
            } catch (e: Exception) {
                null
            }

            uiStatus.invoke( UIStatus.Error("${errorResponse?.errorDescription}"))

        }



    }


}