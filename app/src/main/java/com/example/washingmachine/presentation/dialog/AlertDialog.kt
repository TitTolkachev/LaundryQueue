package com.example.washingmachine.presentation.dialog

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.washingmachine.R
import com.example.washingmachine.databinding.DialogFragmentAlertBinding

class AlertDialog : DialogFragment() {

    private lateinit var binding: DialogFragmentAlertBinding

    interface IAlertDialogExitListener {
        fun alertDialogExit()
    }

    interface IAlertDialogListener {
        fun alertDialogRetry(alertType: AlertType)
        fun onAlertDialogDismiss()
    }

    private var exitListener: IAlertDialogExitListener? = null
    private var dialogListener: IAlertDialogListener? = null

    private lateinit var alert: AlertType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentAlertBinding.inflate(inflater)

        val ordinal = arguments?.getInt(getString(R.string.alert_dialog_data_text)) ?: 0

        alert = AlertType.values().first {
            it.ordinal == (ordinal)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView8.text = getAlertMessage(alert)

        when (getAlertType(alert)) {
            AlertDisplayType.YES_NO -> {
                binding.imageView4.setImageResource(R.drawable.icon_admin_machines_coin)
                binding.alertDialogExitBtn.text = getString(R.string.alert_dialog_no_btn_text)
                binding.alertDialogRetryBtn.text = getString(R.string.alert_dialog_yes_btn_text)
                binding.alertDialogExitBtn.setOnClickListener {
                    dialog?.dismiss()
                }
                binding.alertDialogRetryBtn.setOnClickListener {
                    dialog?.dismiss()
                    dialogListener?.alertDialogRetry(alert)
                }
            }

            AlertDisplayType.VALIDATION -> {
//                binding.imageView4.setImageResource(R.drawable.error_icon_1)
//                binding.alertDialogExitBtn.visibility = View.GONE
//                binding.alertDialogRetryBtn.text =
//                    getString(R.string.alert_button_text_understandable)
//                binding.alertDialogRetryBtn.setOnClickListener {
//                    dialog?.dismiss()
//                }
            }

            AlertDisplayType.INFO_ONE -> {
                binding.imageView4.setImageResource(R.drawable.icon_admin_machines_coin)
                binding.alertDialogExitBtn.visibility = View.GONE
                binding.alertDialogRetryBtn.text = getString(R.string.alert_button_text_lol)
                binding.alertDialogRetryBtn.setOnClickListener {
                    dialog?.dismiss()
                }
            }

            AlertDisplayType.INFO_TWO -> {
//                binding.imageView4.setImageResource(R.drawable.error_icon_3)
//                binding.alertDialogExitBtn.visibility = View.GONE
//                binding.alertDialogRetryBtn.text = getString(R.string.alert_button_text_lol)
//                binding.alertDialogRetryBtn.setOnClickListener {
//                    dialog?.dismiss()
//                }
            }

            AlertDisplayType.SERVER_ERROR -> {
//                binding.imageView4.setImageResource(R.drawable.error_icon_4)
//
//                binding.alertDialogExitBtn.setOnClickListener {
//                    SaveTokenToLocalStorageUseCase(
//                        TokenStorageRepository(
//                            SharedPrefTokenStorage(
//                                requireContext()
//                            )
//                        )
//                    ).execute(
//                        Token("", "")
//                    )
//                }
//
//                binding.alertDialogRetryBtn.setOnClickListener {
//                    dialog?.dismiss()
//                    dialogListener?.alertDialogRetry()
//                }
            }
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dialogListener?.onAlertDialogDismiss()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dialogListener = (parentFragment ?: activity) as IAlertDialogListener?
            exitListener = activity as IAlertDialogExitListener?
        } catch (e: ClassCastException) {
            dialog?.dismiss()
        }
    }

    private fun getAlertMessage(alert: AlertType): String {
        return when (alert) {
            AlertType.INTENT_FOR_QUEUE_BOOKING -> getString(R.string.alert_text_intent_for_queue_booking)
            AlertType.INTENT_FOR_SLOT_CHECKOUT -> getString(R.string.alert_text_intent_for_slot_checkout)
            AlertType.DEFAULT -> getString(R.string.alert_text_default)
        }
    }

    private fun getAlertType(alert: AlertType): AlertDisplayType {
        return when (alert) {
            AlertType.INTENT_FOR_QUEUE_BOOKING -> AlertDisplayType.YES_NO
            AlertType.INTENT_FOR_SLOT_CHECKOUT -> AlertDisplayType.YES_NO
            AlertType.DEFAULT -> AlertDisplayType.INFO_ONE
        }
    }

    private enum class AlertDisplayType {
        VALIDATION,
        SERVER_ERROR,
        INFO_ONE,
        INFO_TWO,
        YES_NO
    }
}