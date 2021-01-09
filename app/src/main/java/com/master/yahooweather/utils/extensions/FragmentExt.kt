package com.master.yahooweather.utils.domain.extensions

import android.widget.Toast
import androidx.appcompat.app.AlertDialog

/**
 * Created by MasterChen on 2020/12/28
 */
fun androidx.fragment.app.Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(message, duration)

inline fun androidx.fragment.app.Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) = activity?.alertDialog(body)
