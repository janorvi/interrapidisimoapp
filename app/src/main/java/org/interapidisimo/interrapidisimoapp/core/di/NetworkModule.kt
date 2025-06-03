package org.interapidisimo.interrapidisimoapp.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import org.interapidisimo.interrapidisimoapp.core.utils.*
import org.interapidisimo.interrapidisimoapp.data.network.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    @Named("GeneralRetrofit")
    fun provideGeneralRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.INTERRAPIDISIMO_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("LoginRetrofit")
    fun provideLoginRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.INTERRAPIDISIMO_LOGIN_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)     // Tiempo para conectar al servidor
            .readTimeout(2, TimeUnit.SECONDS)        // Tiempo de espera para leer la respuesta
            .writeTimeout(2, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGetCurrentVersionApiClient(@Named("GeneralRetrofit") retrofit: Retrofit): GetCurrentVersionApiClient {
        return retrofit.create(GetCurrentVersionApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginApiClient(@Named("LoginRetrofit") retrofit: Retrofit): LoginApiClient {
        return retrofit.create(LoginApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideGetTablesInfoApiClient(@Named("GeneralRetrofit") retrofit: Retrofit): GetTablesInfoApiClient {
        return retrofit.create(GetTablesInfoApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideGetLocalitiesApiClient(@Named("GeneralRetrofit") retrofit: Retrofit): GetLocalitiesApiClient {
        return retrofit.create(GetLocalitiesApiClient::class.java)
    }
}