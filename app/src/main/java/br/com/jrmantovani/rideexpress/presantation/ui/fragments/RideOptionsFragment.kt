package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jrmantovani.rideexpress.R
import br.com.jrmantovani.rideexpress.core.ErrorAlert
import br.com.jrmantovani.rideexpress.core.LoadingAlert
import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.core.toKm
import br.com.jrmantovani.rideexpress.data.remote.model.Driver
import br.com.jrmantovani.rideexpress.data.remote.model.RideConfirmRequest
import br.com.jrmantovani.rideexpress.databinding.FragmentRideOptionsBinding
import br.com.jrmantovani.rideexpress.presantation.ui.adapter.MotoristAdapter
import br.com.jrmantovani.rideexpress.presantation.viewmodel.RideConfirmViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RideOptionsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentRideOptionsBinding
    private lateinit var motoristAdapter: MotoristAdapter
    private lateinit var loadingAlert: LoadingAlert
    private lateinit var errorAlert: ErrorAlert
    private val rideOptionsFragmentArgs: RideOptionsFragmentArgs by navArgs()
    private val rideConfirmViewModel: RideConfirmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRideOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeMap()
        initializeMotoristAdapter()
        initializeListeners()
    }

    private fun initializeListeners() {
        binding.btnBackRideOptions.setOnClickListener {
            val navController = findNavController()

            navController.popBackStack()
        }
    }

    private fun initializeMap() {

        val mapFragment = binding.mapView.getFragment<SupportMapFragment>()
        mapFragment.getMapAsync(this)
    }

    private fun initializeMotoristAdapter() {



        motoristAdapter = MotoristAdapter{ motorist ->

            loadingAlert = LoadingAlert(requireContext())
            errorAlert = ErrorAlert(requireContext())

            val rideConfirmRequest = RideConfirmRequest(

                customerId = rideOptionsFragmentArgs.customerId,
                origin = rideOptionsFragmentArgs.origin,
                destination = rideOptionsFragmentArgs.destination,
                distance = rideOptionsFragmentArgs.rideEstimate.distance.toKm(),
                driver = Driver(id=motorist.id, motorist.name),
                duration = rideOptionsFragmentArgs.rideEstimate.duration.toString(),
                value = motorist.value.toString()
            )

            rideConfirmViewModel.rideConfirm(rideConfirmRequest){status->
                when(status){
                    is UIStatus.Loading->{
                        loadingAlert.show("Confirmando viagem")
                    }
                    is UIStatus.Success->{

                        val rideconfirm = status.data

                        if(rideconfirm.success){
                            val navController = findNavController()
                            navController.navigate(R.id.rideHistoryFragment)


                        }else{
                            errorAlert.show("Erro", "Erro ao escolher motorista")
                        }
                        loadingAlert.close()

                    }
                    is UIStatus.Error ->{
                        loadingAlert.close()

                        errorAlert.show("Erro", status.errorMessage)


                    }
                }

            }
        }
        motoristAdapter.addList(rideOptionsFragmentArgs.rideEstimate.motorists)

        binding.rvMotorist.adapter = motoristAdapter
        binding.rvMotorist.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )


    }

    override fun onMapReady(googleMap: GoogleMap) {
        drawRoute(googleMap)
    }

    private fun drawRoute(googleMap: GoogleMap) {
        val boundsBuilder = LatLngBounds.Builder()
        val rideEstimate = rideOptionsFragmentArgs.rideEstimate
       val places = arrayListOf(
            Place("Origem", LatLng(rideEstimate.latitudeOrigin ,  rideEstimate.longitudeOrigin), rideOptionsFragmentArgs.origin),
            Place("Destino", LatLng(rideEstimate.latitudeDestination, rideEstimate.longitudeDestination), rideOptionsFragmentArgs.destination)
        )

        places.forEach { place ->
            googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
                    .snippet(place.address)
            )
            boundsBuilder.include(place.latLng)
        }


        val polylineOptions = PolylineOptions()
            .addAll(places.map { it.latLng })
            .color(0xFF0000FF.toInt())
            .width(5f)
        googleMap.addPolyline(polylineOptions)


        val bounds = boundsBuilder.build()
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }


}


data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String
)