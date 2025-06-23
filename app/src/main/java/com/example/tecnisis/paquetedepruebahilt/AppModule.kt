package com.example.tecnisis.paquetedepruebahilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindGreetingService(
        greetingServiceImpl: GreetingServiceImpl
    ): GreetingService
}