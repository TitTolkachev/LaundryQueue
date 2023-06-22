package com.example.washingmachine.presentation.screens.adminmachines

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityAdminMachinesBinding
import com.example.washingmachine.presentation.screens.addmachine.AddMachineActivity
import com.example.washingmachine.presentation.screens.adminmachines.adapters.AdminMachineCardActionListener
import com.example.washingmachine.presentation.screens.adminmachines.adapters.AdminMachinesAdapter
import com.example.washingmachine.presentation.screens.editmachine.EditMachineActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AdminMachinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMachinesBinding
    private lateinit var adapter: AdminMachinesAdapter
    private lateinit var viewModel: AdminMachinesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMachinesBinding.inflate(layoutInflater)
        viewModel = getViewModel()
        viewModel.refresh()
        setContentView(binding.root)

        binding.button7.setOnClickListener {
            val intent = Intent(this, AddMachineActivity::class.java)
            startActivity(intent)
        }

        binding.adminMachinesRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        initRecyclerViews()
    }

    private fun initRecyclerViews() {

        val actionListenerImpl = object : AdminMachineCardActionListener {
            override fun onItemClicked(machineId: String) {
                onMachineCardClicked(machineId)
            }
        }

        val linearLayoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        adapter = AdminMachinesAdapter(actionListenerImpl)
        binding.recyclerView.adapter = adapter


        viewModel.getMachinesLiveData().observe(this) {
            adapter.data = it
            binding.adminMachinesRefreshLayout.isRefreshing = false
            binding.textView5.text = viewModel.getDormitoryName() + " dormitory"
        }
    }

    private fun onMachineCardClicked(machineId: String) {
        val intent = Intent(this, EditMachineActivity::class.java)
        intent.putExtra(getString(R.string.machine_id), machineId)
        startActivity(intent)
    }
}