package com.damilola.remote.graphqlremotes.di

import com.damilola.remote.graphqlremotes.libCurrency.SymbolFlagsRemoteController
import com.damilola.remote.graphqlremotes.libCurrency.impl.SymbolFlagsRemoteControllerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface GraphQlRemoteModule {


    @get:Binds
    val SymbolFlagsRemoteControllerImpl.bindSymbolFlagsRemoteController: SymbolFlagsRemoteController
}