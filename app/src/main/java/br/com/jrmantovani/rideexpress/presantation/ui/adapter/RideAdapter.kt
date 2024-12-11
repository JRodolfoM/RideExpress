package br.com.jrmantovani.rideexpress.presantation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jrmantovani.rideexpress.databinding.ItemRvMotoristBinding
import br.com.jrmantovani.rideexpress.databinding.ItemRvRideBinding


import br.com.jrmantovani.rideexpress.domain.model.Ride

class RideAdapter(): RecyclerView.Adapter<RideAdapter.RideViewHolder>() {


    private var listRide = emptyList<Ride>()
    fun addList( list: List<Ride>){
        listRide = list
        notifyDataSetChanged()
    }


    inner class RideViewHolder(
        private val binding: ItemRvRideBinding
    ) : ViewHolder(binding.root){


        fun bind(ride: Ride){

            binding.textDateTime.text = ride.date
            binding.textDriverName.text = ride.driverName
            binding.textOrigin.text = ride.origin
            binding.textDestination.text = ride.destination
            binding.textDistance.text = ride.distance.toString()
            binding.textTime.text = ride.duration
            binding.textValue.text = ride.value.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RideViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRvRide = ItemRvRideBinding.inflate(inflater, parent, false)
        return RideViewHolder(itemRvRide)
    }

    override fun getItemCount(): Int {
        return listRide.size
    }

    override fun onBindViewHolder(holder: RideViewHolder, position: Int) {
        val ride = listRide[position]
        holder.bind(ride)
    }

}