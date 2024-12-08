package br.com.jrmantovani.rideexpress.presantation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.jrmantovani.rideexpress.databinding.FragmentRideOptionsBinding


class RideOptionsFragment : Fragment() {

 private lateinit var binding: FragmentRideOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
         binding = FragmentRideOptionsBinding.inflate(inflater, container, false)

        return binding.root
    }


}