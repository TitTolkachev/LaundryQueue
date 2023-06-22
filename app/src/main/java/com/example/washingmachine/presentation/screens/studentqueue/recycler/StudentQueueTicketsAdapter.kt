package com.example.washingmachine.presentation.screens.studentqueue.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.StudentQueueTicketBinding
import com.example.washingmachine.presentation.screens.studentqueue.model.MachineSlot
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.concurrent.TimeUnit

class StudentQueueTicketsAdapter(
    private val clickListener: QueueTicketClickListener,
    private val data: MutableList<MachineSlot>
) :
    RecyclerView.Adapter<StudentQueueTicketsAdapter.StudentQueueTicketViewHolder>() {

    private fun getZoneOffset(): Long {
        val mCalendar: Calendar = GregorianCalendar()
        val mTimeZone = mCalendar.timeZone
        val mGMTOffset = mTimeZone.rawOffset.toLong()
        return TimeUnit.HOURS.convert(mGMTOffset, TimeUnit.MILLISECONDS)
    }


    inner class StudentQueueTicketViewHolder(var binding: StudentQueueTicketBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MachineSlot) {

            binding.machineName.text = item.machineName
            binding.positionInQueue.text = "You are ${item.number} in queue"

            binding.startTime.visibility = View.INVISIBLE

            if (item.machineStatus == "READY_TO_WORK" && item.number == 1) {
                binding.quitButton.visibility = View.VISIBLE
                binding.startButton.visibility = View.VISIBLE
            }

            if (item.machineStatus == "WORKING" && item.number == 1) {
                binding.quitButton.visibility = View.INVISIBLE
                binding.startButton.visibility = View.INVISIBLE
                binding.positionInQueue.text = "Your clothes still in progress"
                binding.startTime.visibility = View.VISIBLE


                val dt = LocalDateTime.parse(item.startTime).plusHours(getZoneOffset())
                binding.startTime.text =
                    "Started at " + dt.format(DateTimeFormatter.ofPattern("HH:mm")).toString()
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