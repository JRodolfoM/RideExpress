package br.com.jrmantovani.rideexpress.presantation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import br.com.jrmantovani.rideexpress.core.formatAsCurrency
import br.com.jrmantovani.rideexpress.databinding.ItemRvMotoristBinding
import br.com.jrmantovani.rideexpress.domain.model.Motorist

class MotoristAdapter(
    val onClick: (Motorist) -> Unit
): RecyclerView.Adapter<MotoristAdapter.MotoristViewHolder>() {

    private var listMotorist = emptyList<Motorist>()
    fun addList( list: List<Motorist>){
        listMotorist = list
        notifyDataSetChanged()
    }


    inner class MotoristViewHolder(
        private val binding: ItemRvMotoristBinding
    ) : ViewHolder(binding.root){
        private var lastClickTime: Long = 0

        fun bind(motorist: Motorist){
            binding.name.text = motorist.name
            binding.textDescription.text = motorist.description
            binding.textVehicle.text = motorist.vehicle
            binding.textValue.text = motorist.value.formatAsCurrency()
            binding.rating.rating = motorist.rating.toFloat()

            binding.btnChoice.setOnClickListener {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastClickTime > 1500) {
                    lastClickTime = currentTime
                    onClick(motorist)
                }

            }

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