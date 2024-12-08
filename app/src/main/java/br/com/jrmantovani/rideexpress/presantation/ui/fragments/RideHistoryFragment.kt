package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.jrmantovani.rideexpress.databinding.FragmentRideHistoryBinding


class RideHistoryFragment : Fragment() {

private lateinit var binding: FragmentRideHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRideHistoryBinding.inflate(inflater, container, false)

        return binding.root
    }


}