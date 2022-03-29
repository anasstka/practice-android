package com.example.app_wear.presentation.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

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