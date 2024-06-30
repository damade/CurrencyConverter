package com.damilola.core_android.utils.ui_providers

import android.annotation.SuppressLint
import android.view.View
import com.damilola.core_android.ext.setShowProgress
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SnackBarProvider @Inject constructor() {


    @SuppressLint("ShowToast")
    fun showShortMessage(view: View, string: String) {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE).show()
    }

    @SuppressLint("ShowToast")
    fun showShortSlideMessage(view: View, string: String) {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
    }

    @SuppressLint("ShowToast")
    fun showShortMessageWithAnchor(view: View, string: String, anchorView: View) {
        Snackbar.make(view, string, Snackbar.LENGTH_SHORT)
            .setAnchorView(anchorView)
            .setAnimationMode(Snackbar.ANIMATION_MODE_FADE).show()
    }

    @SuppressLint("ShowToast")
    fun showLongMessage(view: View, string: String) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
    }

    @SuppressLint("ShowToast")
    fun showMessageAndDisableLoader(view: View, string: String, button: MaterialButton) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE).show()
        button.setShowProgress(false)
    }
}