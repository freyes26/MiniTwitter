package com.example.minitwitter.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.minitwitter.R

class ErrorDialog  : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val buider = AlertDialog.Builder(it)
            buider.setTitle(getString(R.string.title_dialog_error))
                .setNegativeButton(R.string.close){ dialog, _ ->
                    dialog.dismiss()
                }
            buider.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}