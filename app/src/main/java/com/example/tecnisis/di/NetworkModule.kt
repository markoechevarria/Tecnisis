package com.example.tecnisis.di

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
object AppModule {

    private const val BASE_URL = "http://10.0.2.2:8000/tecnisis/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}