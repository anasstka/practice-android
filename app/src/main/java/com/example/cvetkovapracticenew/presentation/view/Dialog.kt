package com.example.cvetkovapracticenew.presentation.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

// функция для создания диалоговых окон с ошибками
fun dialog(
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