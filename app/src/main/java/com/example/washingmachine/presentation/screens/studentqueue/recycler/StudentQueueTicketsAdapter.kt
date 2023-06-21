package com.example.washingmachine.presentation.screens.studentqueue.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.StudentQueueTicketBinding
import com.example.washingmachine.presentation.screens.studentqueue.model.MachineSlot

class StudentQueueTicketsAdapter(
    private val clickListener: QueueTicketClickListener,
    private val data: MutableList<MachineSlot>
) :
    RecyclerView.Adapter<StudentQueueTicketsAdapter.StudentQueueTicketViewHolder>() {


    inner class StudentQueueTicketViewHolder(var binding: StudentQueueTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MachineSlot) {

            binding.machineName.text = item.machineName
            binding.positionInQueue.text = "You are ${item.number} in queue"

            if (item.machineStatus == "READY_TO_WORK" && item.number == 1) {
                binding.quitButton.visibility = View.VISIBLE
                binding.startButton.visibility = View.VISIBLE
            }

            if (item.machineStatus == "WORKING" && item.number == 1) {
                binding.quitButton.visibility = View.INVISIBLE
                binding.startButton.visibility = View.INVISIBLE
                binding.positionInQueue.text = "Your clothes still in progress"
            }

            if ((item.number ?: 0) > 1) {
                binding.quitButton.visibility = View.VISIBLE
            }

            if ((item.number ?: 0) > 1) {
                binding.startButton.visibility = View.INVISIBLE
            }

            binding.quitButton.setOnClickListener {
                clickListener.quiteQueue()
            }
            binding.startButton.setOnClickListener {
                clickListener.start()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentQueueTicketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StudentQueueTicketBinding.inflate(inflater, parent, false)
        return StudentQueueTicketViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: StudentQueueTicketViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

interface QueueTicketClickListener {
    fun start()

    fun quiteQueue()
}