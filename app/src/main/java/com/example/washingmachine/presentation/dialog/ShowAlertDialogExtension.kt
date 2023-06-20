package com.example.washingmachine.presentation.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.washingmachine.R

fun AppCompatActivity.showAlertDialog(alert: AlertType) {

    val dialog = AlertDialog()
    val bundle = Bundle()
    bundle.putInt(
        getString(R.string.alert_dialog_data_text),
        alert.ordinal
    )
    dialog.arguments = bundle
    dialog.show(supportFragmentManager, "alert_dialog")
}