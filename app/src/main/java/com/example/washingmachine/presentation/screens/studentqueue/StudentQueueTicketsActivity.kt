package com.example.washingmachine.presentation.screens.studentqueue

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.washingmachine.databinding.StudentQueueListBinding
import com.example.washingmachine.presentation.screens.studentqueue.recycler.QueueTicketClickListener
import com.example.washingmachine.presentation.screens.studentqueue.recycler.StudentQueueTicketsAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel

class StudentQueueTicketsActivity : AppCompatActivity() {
    lateinit var binding: StudentQueueListBinding
    lateinit var viewModel: StudentQueueTicketsViewModel
    lateinit var adapter: StudentQueueTicketsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StudentQueueListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()
        viewModel.refresh()

        adapter = StudentQueueTicketsAdapter(object : QueueTicketClickListener {
            override fun start() {
                viewModel.startMachine()
                viewModel.refresh()
            }

            override fun quiteQueue() {
                viewModel.checkOutQueue()
                viewModel.refresh()
            }
        }, viewModel.getMyTickets())


        binding.ticketsList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.ticketsList.adapter = adapter

        viewModel.getMyTicketsLiveData().observe(this) {
            if (it == true) {
                adapter.notifyDataSetChanged()
            }
        }
    }
}