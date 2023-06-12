package com.example.washingmachine.presentation.screens.queue.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.ItemQueueAvailableSlotBinding
import com.example.washingmachine.databinding.ItemQueueNotAvailableSlotBinding
import com.example.washingmachine.databinding.ItemQueueSelfSlotBinding
import com.example.washingmachine.presentation.screens.queue.model.QueueSlot
import com.example.washingmachine.presentation.screens.queue.model.QueueSlotTypes

class QueueAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data = mutableListOf<QueueSlot>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class QueueAvailableViewHolder(val binding: ItemQueueAvailableSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QueueSlot, index: Int) {
            with(binding) {
                //TODO
                textView3.text = index.toString()
            }
        }
    }

    class QueueNotAvailableViewHolder(val binding: ItemQueueNotAvailableSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QueueSlot, index: Int) {
            with(binding) {
                //TODO
                root.tag = item.id
                textView3.text = index.toString()
            }
        }
    }

    class QueueSelfViewHolder(val binding: ItemQueueSelfSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: QueueSlot, index: Int) {
            with(binding) {
                //TODO
                textView3.text = index.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            1 -> {
                val binding = ItemQueueAvailableSlotBinding.inflate(inflater, parent, false)
                QueueAvailableViewHolder(binding)
            }

            2 -> {
                val binding = ItemQueueNotAvailableSlotBinding.inflate(inflater, parent, false)
                QueueNotAvailableViewHolder(binding)
            }

            else -> {
                val binding = ItemQueueSelfSlotBinding.inflate(inflater, parent, false)
                QueueSelfViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is QueueAvailableViewHolder -> {
                holder.bind(data[position], position + 1)
            }

            is QueueNotAvailableViewHolder -> {
                holder.bind(data[position], position + 1)
            }

            is QueueSelfViewHolder -> {
                holder.bind(data[position], position + 1)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        if (data[position].type == QueueSlotTypes.AVAILABLE)
            return 1
        if (data[position].type == QueueSlotTypes.NOT_AVAILABLE)
            return 2
        return 3
    }
}