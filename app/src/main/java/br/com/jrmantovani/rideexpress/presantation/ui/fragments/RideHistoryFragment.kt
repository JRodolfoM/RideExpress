package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import br.com.jrmantovani.rideexpress.databinding.FragmentRideHistoryBinding
import br.com.jrmantovani.rideexpress.domain.model.Ride
import br.com.jrmantovani.rideexpress.presantation.ui.adapter.MotoristAdapter
import br.com.jrmantovani.rideexpress.presantation.ui.adapter.RideAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideHistoryFragment : Fragment() {

private lateinit var binding: FragmentRideHistoryBinding
    private lateinit var rideAdapter: RideAdapter

    val listRide = listOf(
        Ride(
            id = 1,
            date = "2024-12-09 14:30",
            origin = "123 Main Street",
            destination = "456 Elm Avenue",
            driverName = "John Doe",
            distance = 15.0,
            duration = "30 mins",
            value = 20.0
        ),
        Ride(
            id = 2,
            date = "2024-12-08 10:00",
            origin = "789 Pine Lane",
            destination = "321 Oak Drive",
            driverName = "Jane Smith",
            distance = 10.5,
            duration = "20 mins",
            value = 15.0
        ),
        Ride(
            id = 3,
            date = "2024-12-07 18:45",
            origin = "555 Maple Road",
            destination = "888 Birch Boulevard",
            driverName = "Alex Johnson",
            distance = 25.0,
            duration = "50 mins",
            value = 35.0
        )
    )

    private val mockDrivers = listOf("Todos", "1 - Homer Simpson", "2 - Dominic Toretto", "3 - James Bond")

    private lateinit var sipnerAdapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRideHistoryBinding.inflate(inflater, container, false)
        initializeMotoristSpnerAdapter()
        initializeRideAdapter()
        initializeListeners()
        return binding.root
    }

    private fun initializeRideAdapter() {
        rideAdapter = RideAdapter()
        rideAdapter.addList(listRide)
        binding.rvRide.adapter = rideAdapter
        binding.rvRide.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )

    }

    private fun initializeMotoristSpnerAdapter() {


        sipnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_dropdown_item, mockDrivers)

        sipnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.spinnerMotorist.adapter = sipnerAdapter

    }

    fun initializeListeners(){
        binding.btnFilter.setOnClickListener{
            testSpinner()
        }
    }
    fun testSpinner(){
        val selectedItemPosition = binding.spinnerMotorist.selectedItemPosition
        Toast.makeText(requireContext(), mockDrivers[selectedItemPosition]+"Posicao selecionada $selectedItemPosition", Toast.LENGTH_SHORT).show()
    }


}