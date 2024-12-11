package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jrmantovani.rideexpress.R
import br.com.jrmantovani.rideexpress.databinding.FragmentRideOptionsBinding
import br.com.jrmantovani.rideexpress.domain.model.Motorist
import br.com.jrmantovani.rideexpress.presantation.ui.adapter.MotoristAdapter
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
    private val rideOptionsFragmentArgs: RideOptionsFragmentArgs by navArgs()


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

        motoristAdapter = MotoristAdapter()
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