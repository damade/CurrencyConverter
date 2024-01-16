package com.damilola.core_android.utils.resource_providers

import android.graphics.drawable.Drawable
import android.text.Spanned

interface ResourceProvider {
    fun getString(resourceId: Int): String
    fun getStringArray(resourceId: Int): Array<String>
    fun getString(resourceId: Int, vararg args: Any): String
    fun getText(resourceId: Int, vararg args: Any): Spanned
    fun getColor(color: Int): Int
    fun getDrawable(drawable: Int): Drawable?

}