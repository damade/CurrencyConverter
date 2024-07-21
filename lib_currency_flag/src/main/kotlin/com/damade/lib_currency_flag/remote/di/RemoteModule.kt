package com.damade.lib_currency_flag.remote.di

import com.damade.lib_currency_flag.data.contract.remote.SymbolFlagsRemote
import com.damade.lib_currency_flag.remote.impl.SymbolFlagsRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
internal interface RemoteModule {

    @get:Binds
    val SymbolFlagsRemoteImpl.bindSymbolFlagsRemote: SymbolFlagsRemote



}