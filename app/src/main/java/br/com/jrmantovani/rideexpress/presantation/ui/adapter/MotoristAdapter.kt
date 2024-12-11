package br.com.jrmantovani.rideexpress.presantation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jrmantovani.rideexpress.databinding.ItemRvMotoristBinding
import br.com.jrmantovani.rideexpress.domain.model.Motorist

class MotoristAdapter(): RecyclerView.Adapter<MotoristAdapter.MotoristViewHolder>() {

    private var listMotorist = emptyList<Motorist>()
    fun addList( list: List<Motorist>){
        listMotorist = list
        notifyDataSetChanged()
    }


    inner class MotoristViewHolder(
        private val binding: ItemRvMotoristBinding
    ) : ViewHolder(binding.root){


        fun bind(motorist: Motorist){
            binding.name.text = motorist.name
            binding.textDescription.text = motorist.description
            binding.textVehicle.text = motorist.vehicle
            binding.textValue.text = motorist.value.toString()
            binding.rating.rating = motorist.rating.toFloat()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MotoristViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemRvMotorist = ItemRvMotoristBinding.inflate(inflater, parent, false)
        return MotoristViewHolder(itemRvMotorist)
    }


    override fun getItemCount(): Int {
       return listMotorist.size
    }
    override fun onBindViewHolder(holder: MotoristViewHolder, position: Int) {
        val motorist = listMotorist[position]
        holder.bind(motorist)
    }

}