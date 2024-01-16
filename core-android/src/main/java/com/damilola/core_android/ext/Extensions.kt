package com.damilola.core_android.ext

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import java.util.*
import kotlin.contracts.contract


/**
 * Returns the `location` object as a human-readable string.
 */
fun Location?.toText(): String {
    return if (this != null) {
        "$latitude,$longitude"
    } else {
        "Unknown location"
    }
}




/** Returns the consumer friendly device name  */
fun getDeviceModelName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    return if (model.startsWith(manufacturer)) {
        model.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    } else "$manufacturer $model".replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.R)
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun getDeviceSerial(): String? {

    return if (Build.VERSION.SDK_INT <= 25) {
        Build.SERIAL
    } else if (Build.VERSION.SDK_INT in 26..28){
        Build.getSerial()
    }
//    else if(Build.VERSION.SDK_INT >= 29){
//        Build.getSerial()
//    }
    else{
        "null"
    }
}


@SuppressLint("MissingPermission")
@RequiresApi(Build.VERSION_CODES.R)
@RequiresPermission(Manifest.permission.READ_PHONE_STATE)
fun getDeviceIMEI(context: Context): String?{
    val telephonyManager =  context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?

    if (telephonyManager != null) {
        return if (Build.VERSION.SDK_INT >=  26 && Build.VERSION.SDK_INT <= 28) {
            telephonyManager.imei
        } else if(Build.VERSION.SDK_INT <= 25){
            telephonyManager.deviceId
        }
        else{
            getDeviceIMEIMakeShift(context)
        }
    }
    return null
}

fun getUniqueId(): String{
    return UUID.randomUUID().toString()
}

fun getDeviceIMEIMakeShift(context: Context): String? {
    var deviceUniqueIdentifier: String? = null
    val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    /*if (null != tm) {
        deviceUniqueIdentifier = tm.deviceId
    }*/
    if (null == deviceUniqueIdentifier || deviceUniqueIdentifier.isEmpty()) {
        deviceUniqueIdentifier = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
    return deviceUniqueIdentifier
}