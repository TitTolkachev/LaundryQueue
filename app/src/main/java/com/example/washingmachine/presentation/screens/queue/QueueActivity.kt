package com.example.washingmachine.presentation.screens.queue

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.washingmachine.R
import com.example.washingmachine.databinding.ActivityQueueBinding
import com.example.washingmachine.presentation.dialog.AlertDialog
import com.example.washingmachine.presentation.dialog.AlertType
import com.example.washingmachine.presentation.dialog.showAlertDialog
import com.example.washingmachine.presentation.screens.queue.adapters.QueueAdapter
import com.example.washingmachine.presentation.screens.queue.adapters.QueueCardActionListener
import com.example.washingmachine.presentation.screens.queue.model.QueueSlot
import com.example.washingmachine.presentation.screens.queue.model.QueueSlotTypes
import com.google.android.material.snackbar.Snackbar


class QueueActivity : AppCompatActivity(), AlertDialog.IAlertDialogListener {

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
        adapter = QueueAdapter(object : QueueCardActionListener {
            override fun onItemClicked(id: String, status: QueueSlotTypes) {
                if (status == QueueSlotTypes.AVAILABLE)
                    onAvailableSlotClicked(id)
                if (status == QueueSlotTypes.SELF)
                    onSlotCancelClicked(id)
            }
        })
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

    private fun onAvailableSlotClicked(id: String) {
        showAlertDialog(AlertType.DEFAULT)//viewModel.alertType.value ?: AlertType.DEFAULT)
    }

    private fun onSlotCancelClicked(id: String) {
        showAlertDialog(AlertType.INTENT_FOR_QUEUE_BOOKING)//viewModel.alertType.value ?: AlertType.DEFAULT)
    }

    override fun alertDialogRetry() {
        Snackbar.make(binding.root, "", Snackbar.LENGTH_LONG)
            .setAction("CLOSE") { }
            .setActionTextColor(Color.GREEN)
            .show()
    }

    override fun onAlertDialogDismiss() {}
}