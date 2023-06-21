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
        showAlertDialog(AlertType.INTENT_FOR_QUEUE_BOOKING)
    }

    private fun onSlotCancelClicked(id: String) {
        showAlertDialog(AlertType.INTENT_FOR_SLOT_CHECKOUT)
    }

    override fun alertDialogRetry(alertType: AlertType) {
        when(alertType) {
            AlertType.INTENT_FOR_QUEUE_BOOKING -> {
                // TODO
                Snackbar.make(binding.root, "You booked a slot in this queue", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE") { }
                    .setActionTextColor(Color.GRAY)
                    .show()
            }
            AlertType.INTENT_FOR_SLOT_CHECKOUT -> {
                // TODO
                Snackbar.make(binding.root, "You are not in this queue now", Snackbar.LENGTH_LONG)
                    .setAction("CLOSE") { }
                    .setActionTextColor(Color.GRAY)
                    .show()
            }
            else -> {}
        }
    }

    override fun onAlertDialogDismiss() {}
}