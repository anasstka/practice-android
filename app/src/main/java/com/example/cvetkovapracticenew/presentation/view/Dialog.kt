package com.example.cvetkovapracticenew.presentation.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

//class Dialog(val message: String) : DialogFragment() {
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            builder.setTitle("Ошибка")
//                .setMessage(message)
//                .setCancelable(true)
//                .setPositiveButton("ОК") { dialog, id ->
//
//                }
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//}