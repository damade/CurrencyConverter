package com.damilola.core_android.utils.common_providers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import androidx.core.graphics.scale


class ImageCompressor @Inject constructor( @ApplicationContext private val context: Context) {

    /**
     * Method for return file path of Gallery image
     *
     * @param context
     * @param uri
     * @return compressed bitmap of the image selected
     */

    @Throws(IOException::class)
    fun compressImage(photoUri: Uri): Bitmap{
            val inputStream: InputStream? =
                context.contentResolver.openInputStream(photoUri)
            val finalBitmap: Bitmap
            var bitmap = BitmapFactory.decodeStream(inputStream)
            if (bitmap.byteCount >= 12262248) {
                val w = bitmap.width //get width
                val h = bitmap.height//get height
                val aspRat = w / h //get aspect ratio
                if (aspRat > 0) {
                    val W = 4000//do whatever you want with width. Fixed, screen size, anything
                    val H = W * aspRat //set the height based on width and aspect ratio

                    finalBitmap = bitmap.scale(W, H, false) //scale the bitmap
                    bitmap = null //save memory on the bitmap called 'image'
                } else {
                    finalBitmap = bitmap
                }
            } else {
                finalBitmap = bitmap
            }
            bitmap.recycle()
            inputStream?.close()

            return finalBitmap
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun confirmImageDate(photoUri: Uri): Boolean{
        var inputStream: InputStream? = null
        try {
           inputStream = context.contentResolver.openInputStream(photoUri)
            val exifInterface = inputStream?.let { ExifInterface(it) }
            // Now you can extract any Exif tag you want
            // Assuming the image is a JPEG or supported raw format

            if (exifInterface != null) {
                val datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME)
                return if(datetime == null){
                    false
                }else{
                    val format = SimpleDateFormat("yyyy:MM:dd HH:mm:ss")
                    val date: Date = format.parse(datetime)
                    DateUtils.isToday(date.time)
                }
            }
        } catch (e: IOException) {
            // Handle any errors

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (ignored: IOException) {
                }
            }
        }

        return false
    }

}