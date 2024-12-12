package br.com.jrmantovani.rideexpress.presantation.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jrmantovani.rideexpress.core.formatAsCurrency
import br.com.jrmantovani.rideexpress.core.formatAsKilometerWithUnit
import br.com.jrmantovani.rideexpress.core.toFormattedDate
import br.com.jrmantovani.rideexpress.databinding.ItemRvRideBinding
import br.com.jrmantovani.rideexpress.domain.model.RideHistory

class RideAdapter(): RecyclerView.Adapter<RideAdapter.RideViewHolder>() {


    private var listRide = emptyList<RideHistory>()
    fun addList( list: List<RideHistory>){
        listRide = list
        notifyDataSetChanged()
    }


    inner class RideViewHolder(
        private val binding: ItemRvRideBinding
    ) : ViewHolder(binding.root){



        fun bind(ride: RideHistory){

            binding.textDateTime.text = ride.date.toFormattedDate("dd/MM/yyyy HH:mm:ss")
            binding.textDriverName.text = ride.driverName
            binding.textOrigin.text = ride.origin
            binding.textDestination.text = ride.destination
            binding.textDistance.text = ride.distance.formatAsKilometerWithUnit(3)
            binding.textTime.text = ride.duration
            binding.textValue.text = ride.value.formatAsCurrency()

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