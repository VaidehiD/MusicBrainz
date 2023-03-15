package com.dice.data.di

import com.dice.data.BuildConfig
import com.dice.data.api.DetailApi
import com.dice.data.api.SearchApi
import com.dice.data.utils.FORMAT
import com.dice.data.utils.FORMAT_JSON
import com.dice.data.utils.USER_AGENT
import com.dice.data.utils.USER_AGENT_VALUE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                chain.proceed(provideRequest(chain))
            }
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().header(USER_AGENT, USER_AGENT_VALUE).build())
            }
            .build()
    }

    private fun provideRequest(chain: Interceptor.Chain): Request {
        val url = chain
            .request()
            .url()
            .newBuilder()
            .addQueryParameter(FORMAT, FORMAT_JSON)
            .build()
        return chain.request().newBuilder().url(url).build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return retrofit.create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDetailsApi(retrofit: Retrofit): DetailApi {
        return retrofit.create(DetailApi::class.java)
    }
}