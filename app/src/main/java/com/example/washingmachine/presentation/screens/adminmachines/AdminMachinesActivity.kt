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
import com.example.washingmachine.presentation.screens.adminmachines.model.AdminMachineCard
import com.example.washingmachine.presentation.screens.editmachine.EditMachineActivity

class AdminMachinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminMachinesBinding
    private lateinit var adapter: AdminMachinesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminMachinesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button7.setOnClickListener {
            val intent = Intent(this, AddMachineActivity::class.java)
            startActivity(intent)
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
//        viewModel...observe(this) {
//            if (it != null) {
//                washingAdapter1.data = it
//            }
//        }


        //TODO
        val data = mutableListOf(
            AdminMachineCard(
                "123", "Active", 3
            ),
            AdminMachineCard(
                "321", "Paused", 5
            ),
            AdminMachineCard(
                "123", "Active", 3
            ),
            AdminMachineCard(
                "321", "Paused", 5
            ),
            AdminMachineCard(
                "123", "Active", 3
            ),
            AdminMachineCard(
                "321", "Paused", 5
            )
        )
        adapter.data = data
    }

    private fun onMachineCardClicked(machineId: String) {
        val intent = Intent(this, EditMachineActivity::class.java)
        intent.putExtra(getString(R.string.machine_id), machineId)
        startActivity(intent)
    }
}