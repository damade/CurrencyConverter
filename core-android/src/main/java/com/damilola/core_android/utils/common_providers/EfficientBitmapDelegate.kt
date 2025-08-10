package com.damilola.core_android.utils.common_providers

import android.graphics.Bitmap

class EfficientBitmapDelegate {

    private var bitmap: Bitmap? = null

    operator fun getValue(thisRef: Any?, property: Any?): Bitmap? {
        return bitmap
    }

    operator fun setValue(thisRef: Any?, property: Any?, value: Bitmap?) {
        if (value?.generationId == bitmap?.generationId) return
        if (value == null || value.generationId != bitmap?.generationId) {
            bitmap?.recycle()
        }
        bitmap = value
    }

}