package com.damilola.core_android.network


import android.Manifest
import androidx.annotation.RequiresPermission
import com.damilola.core.model.NetworkMiddlewareFailure
import com.damilola.core_android.utils.common_providers.NetworkHelper
import com.damilola.core_android.utils.resource_providers.ResourceProvider
import com.damilola.core_android.R
import javax.inject.Inject

class ConnectivityMiddleware @Inject constructor(
    private val connectivityUtils: NetworkHelper,
    private val resourceProvider: ResourceProvider
) : com.damilola.core.network.NetworkMiddleware() {

    override val failure: NetworkMiddlewareFailure
        get() = NetworkMiddlewareFailure(
            middleWareExceptionMessage = resourceProvider.getString(R.string.error_no_network_detected)
        )

    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isValid(): Boolean = connectivityUtils.isNetworkAvailable()
}