package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.com.jrmantovani.rideexpress.core.ErrorAlert
import br.com.jrmantovani.rideexpress.core.LoadingAlert
import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.core.isValid
import br.com.jrmantovani.rideexpress.data.remote.model.RideEstimateRequest
import br.com.jrmantovani.rideexpress.databinding.FragmentHomeBinding
import br.com.jrmantovani.rideexpress.presantation.viewmodel.RideEstimateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var loadingAlert: LoadingAlert
    private lateinit var errorAlert: ErrorAlert
    private var lastClickTime: Long = 0
    private val rideEstimateViewModel: RideEstimateViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        initializeListeners()
        return binding.root
    }

    private fun initializeListeners() {
       binding.btnRideEstimate.setOnClickListener {
           val currentTime = System.currentTimeMillis()
           if (currentTime - lastClickTime > 1500) {
               lastClickTime = currentTime
               val isFieldIDValid = binding.textInputLayoutID.isValid("Informe o id do usuário")
               val isFieldOriginValid = binding.textInputLayoutOrigin.isValid("Informe o endereço de origem")
               val isFieldDestinationValid = binding.textInputLayoutDestination.isValid("Informe o endereço de destino")

               if (isFieldIDValid && isFieldOriginValid && isFieldDestinationValid) {
                   rideEstimate()
               }


           }
       }
    }

    private fun rideEstimate(){
       loadingAlert = LoadingAlert(requireContext())
        errorAlert = ErrorAlert(requireContext())
           val id = binding.editIdUser.text.toString()
            val origin = binding.editOrigin.text.toString()
           val destination = binding.editDestination.text.toString()

//        val id = "1"
//        val origin ="Av. Thomas Edison, 365 - Barra Funda, São Paulo - SP, 01140-000"
//        val destination = "Av. Paulista, 1538 - Bela Vista, São Paulo - SP, 01310-200"

        val  rideEstimateRequest = RideEstimateRequest(
            customerId = id,
            origin = origin,
            destination = destination

        )
        rideEstimateViewModel.getRideEstimate( rideEstimateRequest ){ status->
            when(status){
                is UIStatus.Loading->{
                    loadingAlert.show("Estimativa de viagem")
                }
                is UIStatus.Success->{

                    val rideEstimate = status.data
                    val navController = findNavController()




                  navController.navigate(
                      HomeFragmentDirections.actionHomeFragmentToRideOptionsFragment(rideEstimate, origin, destination, id))
                    loadingAlert.close()

                 }
                is UIStatus.Error ->{
                    loadingAlert.close()

                    errorAlert.show("Erro", status.errorMessage)


                }
            }
        }

    }





}