package com.damilola.core_android.utils.ui_providers

import android.app.AlertDialog
import android.content.Context
import com.damilola.core_android.R
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PromptDialog @Inject constructor() {
    private lateinit var alertDialog : AlertDialog.Builder
    private var callbackResult: CallbackResult? = null

    fun setOnCallbackResult(callbackResult: CallbackResult?) {
        this.callbackResult = callbackResult
    }

    fun showDefaultDialog(context: Context?, title: String?, message: String?) {
        alertDialog = AlertDialog.Builder(context)
        alertDialog.apply {
            setIcon(R.drawable.ic_info)
            setTitle(title)
            setMessage(message)
            setPositiveButton("Dismiss",null)
            setNegativeButton("Proceed") { _, _ ->
                onProceedClicked()
            }
        }.create().show()
    }

    private fun onProceedClicked() {
        if (callbackResult != null) {
            callbackResult!!.onProceedButtonClicked()
        }
    }

    interface CallbackResult {
        fun onProceedButtonClicked()
    }

}