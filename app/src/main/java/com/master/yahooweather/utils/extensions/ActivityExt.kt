package com.master.yahooweather.utils.domain.extensions

import android.app.Activity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

/**
 * Created by MasterChen on 2020/12/28
 */
fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

inline fun Activity.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder): AlertDialog {
    return AlertDialog.Builder(this)
        .body()
        .show()
}
