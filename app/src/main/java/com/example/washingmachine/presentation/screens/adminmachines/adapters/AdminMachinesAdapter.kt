package com.example.washingmachine.presentation.screens.adminmachines.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.ItemAdminMachineCardBinding
import com.example.washingmachine.domain.model.Machine
import com.example.washingmachine.presentation.screens.adminmachines.model.AdminMachineCard

class AdminMachinesAdapter(private val actionListener: AdminMachineCardActionListener) :
    RecyclerView.Adapter<AdminMachinesAdapter.MachinesViewHolder>(),
    View.OnClickListener {

    var data: MutableList<Machine> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MachinesViewHolder(var binding: ItemAdminMachineCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Machine) {
            with(binding) {
                root.tag = item.id
                textView2.text = item.status
                textView15.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MachinesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAdminMachineCardBinding.inflate(inflater, parent, false)
        return MachinesViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MachinesViewHolder, position: Int) {
        holder.bind(data[position])
        holder.binding.root.setOnClickListener(this@AdminMachinesAdapter)
    }

    override fun onClick(v: View) {
        val machineId = v.tag as String
        actionListener.onItemClicked(machineId)
    }
}

interface AdminMachineCardActionListener {
    fun onItemClicked(machineId: String)
}