package com.laundryukurukur

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.annotation.NonNull

class loadingDialog(@NonNull context: Context) : Dialog(context){
init {
    val params = window!!.attributes
    params.gravity = Gravity.CENTER
    window!!.attributes = params
    window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    window!!.setTitle(null)
    setCancelable(false)
    setOnCancelListener(null)
    val view = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
    setContentView(view)
}
    }