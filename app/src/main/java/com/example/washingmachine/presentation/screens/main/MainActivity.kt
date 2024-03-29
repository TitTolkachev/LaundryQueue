package com.example.washingmachine.presentation.screens.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityMainBinding
import com.example.washingmachine.presentation.screens.main.adapters.MachineCardActionListener
import com.example.washingmachine.presentation.screens.main.adapters.MachinesAdapter
import com.example.washingmachine.presentation.screens.queue.QueueActivity
import com.example.washingmachine.presentation.screens.studentprofile.StudentProfileActivity
import com.example.washingmachine.presentation.screens.studentqueue.StudentQueueTicketsActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var washingAdapter: MachinesAdapter
    private lateinit var dryerAdapter: MachinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel()

        initRecyclerViews()
        binding.goToStudentProfile.setOnClickListener {
            openStudentProfile()
        }

        binding.myQueueTickets.setOnClickListener {
            val intent = Intent(this, StudentQueueTicketsActivity::class.java)
            startActivity(intent)
        }

        binding.studentMainRefreshLayout.setOnRefreshListener {
            viewModel.update()
        }
    }

    private fun initRecyclerViews() {

        val actionListenerImpl = object : MachineCardActionListener {
            override fun onItemClicked(machineId: String, machineName: String) {
                onMachineCardClicked(machineId, machineName)
            }
        }

        val linearLayoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = linearLayoutManager
        washingAdapter = MachinesAdapter(actionListenerImpl)
        binding.recyclerView.adapter = washingAdapter


        viewModel.studentProfileData.observe(this) {
            binding.textView5.text = it.dormitory?.number.toString() + " dormitory"
        }

        viewModel.washingMachinesData.observe(this) {
            if (it.isNullOrEmpty()) {
                binding.textView8.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.textView8.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.VISIBLE
                washingAdapter.data = it
            }
            binding.studentMainRefreshLayout.isRefreshing = false
        }

        val linearLayoutManager2 = GridLayoutManager(this, 2)
        binding.recyclerView3.layoutManager = linearLayoutManager2
        dryerAdapter = MachinesAdapter(actionListenerImpl)
        binding.recyclerView3.adapter = dryerAdapter
        viewModel.dryingMachinesData.observe(this) {
            if (it.isNullOrEmpty()) {
                binding.textView9.visibility = View.GONE
                binding.recyclerView3.visibility = View.GONE
            } else {
                binding.textView9.visibility = View.VISIBLE
                binding.recyclerView3.visibility = View.VISIBLE
                dryerAdapter.data = it
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.update()
    }

    private fun onMachineCardClicked(machineId: String, machineName: String) {
        val intent = Intent(this, QueueActivity::class.java)
        intent.putExtra(getString(R.string.machine_id), machineId)
        intent.putExtra(getString(R.string.machine_name), machineName)
        startActivity(intent)
    }

    private fun openStudentProfile() {
        val intent = Intent(this, StudentProfileActivity::class.java)
        startActivity(intent)
    }
}