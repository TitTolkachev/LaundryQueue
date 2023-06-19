package com.example.washingmachine.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityMainBinding
import com.example.washingmachine.presentation.screens.main.adapters.MachineCardActionListener
import com.example.washingmachine.presentation.screens.main.adapters.MachinesAdapter
import com.example.washingmachine.presentation.screens.main.model.MachineCard
import com.example.washingmachine.presentation.screens.profile.ProfileActivity
import com.example.washingmachine.presentation.screens.queue.QueueActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var washingAdapter1: MachinesAdapter
    private lateinit var washingAdapter2: MachinesAdapter
    private lateinit var dryerAdapter1: MachinesAdapter
    private lateinit var dryerAdapter2: MachinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerViews()
        binding.textView7.setOnClickListener { onProfileClicked() }
    }

    private fun initRecyclerViews() {

        val actionListenerImpl = object : MachineCardActionListener {
            override fun onItemClicked(machineId: String) {
                onMachineCardClicked(machineId)
            }
        }

        val linearLayoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        washingAdapter1 = MachinesAdapter(actionListenerImpl)
        binding.recyclerView.adapter = washingAdapter1
//        viewModel...observe(this) {
//            if (it != null) {
//                washingAdapter1.data = it
//            }
//        }

        val linearLayoutManager2 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView2.layoutManager = linearLayoutManager2
        washingAdapter2 = MachinesAdapter(actionListenerImpl)
        binding.recyclerView2.adapter = washingAdapter2
//        viewModel...observe(this) {
//            if (it != null) {
//                washingAdapter2.data = it
//            }
//        }

        val linearLayoutManager3 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView3.layoutManager = linearLayoutManager3
        dryerAdapter1 = MachinesAdapter(actionListenerImpl)
        binding.recyclerView3.adapter = dryerAdapter1
//        viewModel...observe(this) {
//            if (it != null) {
//                dryerAdapter1.data = it
//            }
//        }

        val linearLayoutManager4 =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView4.layoutManager = linearLayoutManager4
        dryerAdapter2 = MachinesAdapter(actionListenerImpl)
        binding.recyclerView4.adapter = dryerAdapter2
//        viewModel...observe(this) {
//            if (it != null) {
//                dryerAdapter2.data = it
//            }
//        }


        //TODO
        val data = mutableListOf(
            MachineCard(
                "123", "Active", 3
            ),
            MachineCard(
                "321", "Paused", 5
            )
        )
        washingAdapter1.data = data
        washingAdapter2.data = data
        dryerAdapter1.data = data
        dryerAdapter2.data = data
    }

    private fun onMachineCardClicked(machineId: String) {
        val intent = Intent(this, QueueActivity::class.java)
        intent.putExtra(getString(R.string.machine_id), machineId)
        startActivity(intent)
    }

    private fun onProfileClicked() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}