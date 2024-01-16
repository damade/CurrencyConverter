package com.damilola.navigation.di

import androidx.navigation.NavController
import com.damilola.core.model.NavigateToPreviousScreen
import com.damilola.core.navigation.Navigator
import com.damilola.navigation.NavigationProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
internal interface NavigationModule {

    @get:Binds
    val NavigationProvider.navigatorProvider: Navigator

    companion object {
        @Provides
        fun provideBackNav(
            navController: NavController
        ): NavigateToPreviousScreen = {
            navController.navigateUp()
        }
    }
}
