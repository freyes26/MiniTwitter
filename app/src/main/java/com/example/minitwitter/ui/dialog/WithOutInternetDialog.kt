package com.example.minitwitter.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.DialogFragment
import com.example.minitwitter.R

class WithOutInternetDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val buider = AlertDialog.Builder(it)
            buider.setTitle(getString(R.string.title_dialog_witouh_internet))
                .setPositiveButton(getString(R.string.settings)){_, _ ->
                    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                    if(it.packageManager?.resolveActivity(intent, 0) != null){
                        startActivity(intent)
                    }
                }
                .setNegativeButton(R.string.close){ dialog, _ ->
                    dialog.dismiss()
                }
            buider.create()
        }?: throw IllegalStateException("Activity cannot be null")
    }
}