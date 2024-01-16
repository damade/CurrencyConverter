package com.damilola.core_android.utils.ui_providers

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.MediaController
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.core.ext.youtubeLink
import com.damilola.core_android.R
import dagger.hilt.android.qualifiers.ApplicationContext
import de.hdodenhof.circleimageview.CircleImageView
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MediaLoader @Inject constructor(@ApplicationContext private val context: Context) {

    private val requestOptions = RequestOptions()
        .placeholder(R.drawable.progress_animation)
        .error(R.drawable.ic_app_logo)
        .centerCrop()
        .dontAnimate()
        .dontTransform()

    private val noCropRequestOptions = RequestOptions()
        .placeholder(R.drawable.progress_animation)
        .error(R.drawable.ic_app_logo)
        .dontAnimate()
        .dontTransform()


    fun loadImage(imageUrl: String, target: ImageView) {
        if (imageUrl.isNotNullOrEmpty()) {
            Glide
                .with(context)
                .load(imageUrl)
                .apply(noCropRequestOptions)
                .into(target)
        } else {
            target.setImageResource(R.drawable.ic_app_logo)
        }
    }

    fun loadUserProfileImage(imageUrl: String, target: AppCompatImageView) {
        if (imageUrl.isNotNullOrEmpty()) {
            Glide
                .with(context)
                .load(imageUrl)
                .centerCrop()
                .apply(requestOptions)
                .into(target)
        } else {
            target.setImageResource(R.drawable.empty_profile_pic)
        }
    }


    fun loadCarerProfileImage(imageUrl: String?, target: ImageView) {
        if (imageUrl.isNotNullOrEmpty()) {
            Glide
                .with(context)
                .load(imageUrl)
                .centerCrop()
                .apply(requestOptions)
                .into(target)
        } else {
            target.setImageResource(R.drawable.carer_profile_image)
        }
    }

    fun loadCircularImage(imageUrl: String?, target: CircleImageView) {
        if (imageUrl.isNotNullOrEmpty()) {
            Glide
                .with(context)
                .load(imageUrl)
                .centerCrop()
                .apply(requestOptions)
                .into(target)
        } else {
            target.setImageResource(R.drawable.ic_app_logo)
        }
    }

    fun loadCircularProfileImage(imageUrl: String?, target: CircleImageView) {
        if (imageUrl.isNotNullOrEmpty()) {
            Glide
                .with(context)
                .load(imageUrl)
                .centerCrop()
                .apply(requestOptions)
                .into(target)
        } else {
            target.setImageResource(R.drawable.carer_profile_image)
        }
    }

    fun loadVideo(videoUrl: String, target: VideoView){
        val position = 1
        val videoUri = Uri.parse(videoUrl)

        // create an object of media controller
       val mediaController =  MediaController(context)
        mediaController.setAnchorView(target)

        // set media controller object for a video view
        target.setMediaController(mediaController)
        target.keepScreenOn = true
        target.setVideoURI(videoUri)
        target.requestFocus()
        //mediaController.show()
        //target.start()

        target.setOnPreparedListener { mp ->
           target.seekTo(position)
            if (position == 0){
                target.start()
            }else{
                target.pause()
            }
        }
        target.setOnCompletionListener { mp ->
            mp.reset()
            mp.release()
        }

        target.setOnErrorListener { mp, what, extra ->
            val alertDialogManager = AlertDialogManager()
            alertDialogManager.showAlertDialog(
                target.context,
                "Error",
                "Something Went Wrong!",
                false
            )
            false
        }

    }


    fun loadWebVideo(videoUrl: String, target: WebView, waitingProgressBar: ProgressBar) {

        val webSettings: WebSettings  = target.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true

        target.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError?) {
                val builder: AlertDialog.Builder =  AlertDialog.Builder(context)
                var message = "SSL Certificate error."
                when (error!!.primaryError) {
                    SslError.SSL_UNTRUSTED -> message = "The certificate authority is not trusted."
                    SslError.SSL_EXPIRED -> message = "The certificate has expired."
                    SslError.SSL_IDMISMATCH -> message = "The certificate Hostname mismatch."
                    SslError.SSL_NOTYETVALID -> message = "The certificate is not yet valid."
                }
                message += " Do you want to continue anyway?"

                builder.setTitle("SSL Certificate Error")
                builder.setMessage(message)
                builder.setPositiveButton("Continue"
                ) { _, _ -> handler.proceed() }
                builder.setNegativeButton("Cancel"
                ) { _, _ -> handler.cancel() }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                waitingProgressBar.visibility = View.VISIBLE
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    waitingProgressBar.setProgress(75, true)
                }
                waitingProgressBar.visibility = View.VISIBLE
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
                waitingProgressBar.visibility = View.GONE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                waitingProgressBar.visibility = View.GONE
            }

            override fun onReceivedHttpError(
                view: WebView,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse
            ) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Toast.makeText(view.context, "HTTP error " + errorResponse.statusCode, Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
        val newUrl = "https://www.youtube.com/embed/${videoUrl.youtubeLink}"
        target.loadUrl(newUrl)
    }
}