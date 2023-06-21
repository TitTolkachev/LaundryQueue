package com.example.washingmachine.presentation.screens.main.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.ItemMainMachineCardBinding
import com.example.washingmachine.presentation.screens.main.model.MachineCard

class MachinesAdapter(private val actionListener: MachineCardActionListener) :
    RecyclerView.Adapter<MachinesAdapter.MachinesViewHolder>(),
    View.OnClickListener {

    var data: MutableList<MachineCard> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MachinesViewHolder(var binding: ItemMainMachineCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MachineCard) {
            with(binding) {
                root.tag = item.id
                textView2.text = item.status
                textView3.text = item.availableSlots.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachinesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainMachineCardBinding.inflate(inflater, parent, false)
        return MachinesViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MachinesViewHolder, position: Int) {
        holder.bind(data[position])
        holder.binding.root.setOnClickListener(this@MachinesAdapter)
    }

    override fun onClick(v: View) {
        val machineId = v.tag as String
        actionListener.onItemClicked(machineId, data.first { it.id == machineId }.name)
    }
}

interface MachineCardActionListener {
    fun onItemClicked(machineId: String, machineName: String)
}