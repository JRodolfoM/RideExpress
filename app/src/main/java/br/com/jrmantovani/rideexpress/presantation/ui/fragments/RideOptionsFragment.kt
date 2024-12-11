package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val motorists = listOf(
        Motorist(
            description = "Experienced driver",
            id = 1,
            name = "Alice",
            rating = 4,
            value = 100.0,
            vehicle = "Sedan"
        ),
        Motorist(
            description = "New driver",
            id = 2,
            name = "Bob",
            rating = 2,
            value = 50.0,
            vehicle = "SUV"
        ),
        Motorist(
            description = "Frequent commuter",
            id = 3,
            name = "Charlie",
            rating = 5,
            value = 150.0,
            vehicle = "Motorcycle"
        ),
    )
    private val places = arrayListOf(
        Place("Av. Pres. Kenedy", LatLng( -23.5215624,  -46.763286699999995)),
        Place("Av. Paulista", LatLng(-23.5615351, -46.6562816))
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRideOptionsBinding.inflate(inflater, container, false)
        initializeMotoristAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeMap()
    }

    private fun initializeMap() {

        val mapFragment = binding.mapView.getFragment<SupportMapFragment>()
        mapFragment.getMapAsync(this)
    }

    private fun initializeMotoristAdapter() {
        motoristAdapter = MotoristAdapter()
        motoristAdapter.addList(motorists)

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


        places.forEach { place ->
            googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
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

//TODO: paa teste do mapa
data class Place(
    val name: String,
    val latLng: LatLng
)