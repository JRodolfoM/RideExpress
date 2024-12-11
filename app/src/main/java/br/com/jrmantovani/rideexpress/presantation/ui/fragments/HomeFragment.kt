package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.jrmantovani.rideexpress.R
import br.com.jrmantovani.rideexpress.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

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
           val navController = findNavController()
           navController.navigate( R.id.rideOptionsFragment )
          // navController.navigate( R.id.rideHistoryFragment )
       }
    }


}