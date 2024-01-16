package com.damilola.core_android.utils.resource_providers

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Html
import android.text.Spanned
import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {

    override fun getString(resourceId: Int): String = context.getString(resourceId)

    override fun getString(
        resourceId: Int,
        vararg args: Any
    ): String {
        return if (args.isNotEmpty()) {
            context.resources.getString(resourceId, *args)
        } else {
            context.resources.getString(resourceId)
        }
    }

    override fun getStringArray(resourceId: Int): Array<String> {
       return context.resources.getStringArray(resourceId)
    }

    override fun getText(resourceId: Int, vararg args: Any): Spanned {
        return if (args.isNotEmpty()) {
            Html.fromHtml(context.resources.getString(resourceId, *args))
        }
        else {
            Html.fromHtml(context.resources.getString(resourceId))
        }
    }

    override fun getColor(color: Int): Int {
        return ResourcesCompat.getColor(context.resources, color, null)
    }

    override fun getDrawable(drawable: Int): Drawable? {
        return ResourcesCompat.getDrawable(context.resources, drawable, null)
    }


}