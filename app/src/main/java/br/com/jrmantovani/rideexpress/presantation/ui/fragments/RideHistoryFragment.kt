package br.com.jrmantovani.rideexpress.presantation.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.jrmantovani.rideexpress.R
import br.com.jrmantovani.rideexpress.core.ErrorAlert
import br.com.jrmantovani.rideexpress.core.LoadingAlert
import br.com.jrmantovani.rideexpress.core.UIStatus
import br.com.jrmantovani.rideexpress.core.isValid

import br.com.jrmantovani.rideexpress.databinding.FragmentRideHistoryBinding
import br.com.jrmantovani.rideexpress.presantation.ui.adapter.RideAdapter
import br.com.jrmantovani.rideexpress.presantation.viewmodel.RideHistoryViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RideHistoryFragment : Fragment() {

private lateinit var binding: FragmentRideHistoryBinding
private lateinit var rideAdapter: RideAdapter
    private lateinit var loadingAlert: LoadingAlert
    private lateinit var errorAlert: ErrorAlert
    private val rideHistoryViewModel: RideHistoryViewModel by viewModels()
    private var lastClickTime: Long = 0
    private val mockDrivers = listOf("Todos", "1 - Homer Simpson", "2 - Dominic Toretto", "3 - James Bond")

    private lateinit var sipnerAdapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRideHistoryBinding.inflate(inflater, container, false)
        initializeToolbar()
        initializeMotoristSpnerAdapter()
        initializeRideAdapter()
        initializeListeners()
        return binding.root
    }

    private fun initializeToolbar() {
        val toolbar = binding.includeToolbar.toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Consultar Histórico"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initializeRideAdapter() {
        rideAdapter = RideAdapter()
        binding.rvRide.adapter = rideAdapter
        binding.rvRide.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )

    }

    private fun initializeMotoristSpnerAdapter() {


        sipnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, mockDrivers)

        sipnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMotorist.adapter = sipnerAdapter

    }

    private fun initializeListeners(){
        binding.btnFilter.setOnClickListener{
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > 1500) {
                lastClickTime = currentTime
                val isValid = binding.textInputLayout.isValid("Este campo não pode estar vazio")

                if (isValid) {
                    filterRide()
                }
            }

        }

        binding.includeToolbar.toolbar.setNavigationOnClickListener {
            findNavController().navigate(R.id.homeFragment)
        }
    }

    private fun filterRide() {
        loadingAlert = LoadingAlert(requireContext())
        errorAlert = ErrorAlert(requireContext())
        val id = binding.editIdUser.text.toString()
        val selectedItemPosition = binding.spinnerMotorist.selectedItemPosition.toString()


        rideHistoryViewModel.getRideHistory(id, selectedItemPosition) { uiStatus ->
            when (uiStatus) {
                is UIStatus.Loading -> {
                    loadingAlert.show("Histórico de Viagens")
                }

                is UIStatus.Success -> {
                    val rideHistory = uiStatus.data
                    if(rideHistory.isEmpty()){
                        binding.textTitle.visibility = View.GONE
                    }else{
                        binding.textTitle.visibility = View.VISIBLE
                    }
                    rideAdapter.addList(rideHistory)
                    loadingAlert.close()
                }

                is UIStatus.Error -> {
                    rideAdapter.addList(emptyList())
                    binding.textTitle.visibility = View.GONE
                    loadingAlert.close()
                    errorAlert.show("Erro", uiStatus.errorMessage)

                }


            }

        }
    }


}