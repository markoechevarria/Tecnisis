package com.example.tecnisis.di

import com.example.tecnisis.data.remote.InterfazRemoteDataSource
import com.example.tecnisis.data.remote.RemoteDataSource
import com.example.tecnisis.data.repository.UsuarioRepositoryImplementacion
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
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