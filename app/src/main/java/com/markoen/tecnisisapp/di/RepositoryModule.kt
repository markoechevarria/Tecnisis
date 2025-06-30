package com.markoen.tecnisisapp.di

import com.markoen.tecnisisapp.data.remote.InterfazRemoteDataSource
import com.markoen.tecnisisapp.data.remote.RemoteDataSource
import com.markoen.tecnisisapp.data.repository.UsuarioRepositoryImplementacion
import com.markoen.tecnisisapp.domain.repository.InterfazUsuarioRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(
        remoteDataSource: RemoteDataSource
    ): InterfazRemoteDataSource

    @Singleton
    @Binds
    abstract fun bindUsuarioRepository(
        usuarioRepositoryImplementacion: UsuarioRepositoryImplementacion
    ): InterfazUsuarioRepository
}