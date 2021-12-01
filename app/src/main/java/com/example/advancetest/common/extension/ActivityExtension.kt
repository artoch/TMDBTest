package com.example.advancetest.common.extension

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.shortToast(@StringRes id:Int){
    Toast.makeText(this, id, Toast.LENGTH_SHORT)
}

fun Activity.lenghtToast(@StringRes id:Int){
    Toast.makeText(this, id, Toast.LENGTH_LONG)
}