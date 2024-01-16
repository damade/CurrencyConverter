package com.damilola.core_android.ext

import android.view.WindowManager
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity


fun AppCompatActivity.blockInput() {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
    )
}

fun AppCompatActivity.unblockInput() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun AppCompatActivity.showShortToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}
