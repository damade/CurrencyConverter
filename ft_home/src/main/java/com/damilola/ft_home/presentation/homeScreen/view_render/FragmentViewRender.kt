package com.damilola.ft_home.presentation.homeScreen.view_render

import android.view.View
import com.damilola.core_android.ext.setShowProgress
import com.damilola.ft_home.databinding.FragmentHomeBinding

internal fun FragmentHomeBinding.showOnLoading(isLoading: Boolean) {
    loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
    convertButton.setShowProgress(showProgress = isLoading)
}