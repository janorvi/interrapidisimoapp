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
            .addInterceptor(ContentTypeInterceptor())
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
    fun provideGetDatabaseSchemaApiClient(@Named("GeneralRetrofit") retrofit: Retrofit): GetDatabaseSchemaApiClient {
        return retrofit.create(GetDatabaseSchemaApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideGetLocalitiesApiClient(@Named("GeneralRetrofit") retrofit: Retrofit): GetLocalitiesApiClient {
        return retrofit.create(GetLocalitiesApiClient::class.java)
    }
}