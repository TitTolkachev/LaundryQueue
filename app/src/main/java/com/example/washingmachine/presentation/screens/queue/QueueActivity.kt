package com.example.washingmachine.presentation.screens.queue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityQueueBinding
import com.example.washingmachine.presentation.screens.queue.adapters.QueueAdapter
import com.example.washingmachine.presentation.screens.queue.model.QueueSlot
import com.example.washingmachine.presentation.screens.queue.model.QueueSlotTypes

class QueueActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQueueBinding

    private lateinit var adapter: QueueAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val machineId = intent.getStringExtra(getString(R.string.machine_id))

        binding.textView4.text = machineId
        binding.textView10.text = "Active"

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView5.layoutManager = layoutManager
        adapter = QueueAdapter()
        binding.recyclerView5.adapter = adapter
//        viewModel...observe(this) {
//            if (it != null) {
//                adapter.data = it
//            }
//        }

        //TODO
        val data = mutableListOf(
            QueueSlot(
                "1",
                QueueSlotTypes.NOT_AVAILABLE,
                ""
            ),
            QueueSlot(
                "2",
                QueueSlotTypes.AVAILABLE,
                ""
            ),
            QueueSlot(
                "3",
                QueueSlotTypes.SELF,
                ""
            ),
            QueueSlot(
                "4",
                QueueSlotTypes.AVAILABLE,
                ""
            ),
            QueueSlot(
                "5",
                QueueSlotTypes.AVAILABLE,
                ""
            ),
        )
        adapter.data = data
    }

    private fun onAvailableSlotClicked(){

    }

    private fun onSlotCancelClicked(){

    }
}