package com.example.app_wear.presenattion.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

fun Dialog(
    context: Context,
    message: String
) {
    val builder = AlertDialog.Builder(context)

    with(builder)
    {
        setMessage(message)
        setPositiveButton("OK", positiveButtonClick)
        show()
    }
}

val positiveButtonClick = { dialog: DialogInterface, which: Int ->
    dialog.dismiss()
}