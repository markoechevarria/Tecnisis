package com.example.tecnisis.di

/**
import com.example.tecnisis.data.remote.InterfazRemoteDataSource
import com.example.tecnisis.data.remote.RemoteDataSource
import com.example.tecnisis.data.remote.services.ApiService
import com.example.tecnisis.data.repository.UsuarioRepositoryImplementacion
import com.example.tecnisis.domain.repository.InterfazUsuarioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/tecnisis/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): InterfazRemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun provideUsuarioRepository(remoteDataSource: InterfazRemoteDataSource): InterfazUsuarioRepository {
        return UsuarioRepositoryImplementacion(remoteDataSource)
    }
}

**/