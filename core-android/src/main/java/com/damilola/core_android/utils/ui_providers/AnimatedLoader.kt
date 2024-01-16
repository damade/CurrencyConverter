package com.damilola.core_android.utils.ui_providers

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.damilola.core_android.ext.blockInput
import com.damilola.core_android.ext.unblockInput
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject
import javax.inject.Singleton


@ActivityRetainedScoped
class AnimatedLoader @Inject constructor() {

    fun showAppLoader(loader: LottieAnimationView){
        loader.isVisible = !loader.isVisible
        loader.setAnimation("app_loader.json")
        loader.playAnimation()
        loader.repeatMode = LottieDrawable.RESTART
    }

    fun showAppLoaderAndBlock(activity: AppCompatActivity, loader: LottieAnimationView){
        loader.visibility = View.VISIBLE
        loader.setAnimation("app_loader.json")
        loader.playAnimation()
        loader.repeatMode = LottieDrawable.RESTART
        activity.blockInput()
    }

    fun cancelAppLoader(loader: LottieAnimationView){
        loader.cancelAnimation()
        loader.isVisible = !loader.isVisible
    }

    fun cancelAppLoaderAndUnBlock(activity: AppCompatActivity,loader: LottieAnimationView){
        loader.cancelAnimation()
        loader.visibility = View.GONE
        activity.unblockInput()
    }

}