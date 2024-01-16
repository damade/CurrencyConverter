package com.damilola.ft_home.model

import com.damilola.core_android.utils.resource_providers.AppString

data class ConversionUi(
    val from: String,
    val to: String,
    val amount: Int,
    val result: Double?,
    val rate: Double?,
    val resultInfo: AppString?,
)
