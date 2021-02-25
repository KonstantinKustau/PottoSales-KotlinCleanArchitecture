package com.thebestdiscountandroid.core.di

import android.content.Context
import com.thebestdiscountandroid.AndroidApplication
import com.thebestdiscountandroid.BuildConfig
import com.thebestdiscountandroid.core.platform.MockInterceptor
import com.thebestdiscountandroid.features.chooseshop.data.ChooseShopRepositoryImpl
import com.thebestdiscountandroid.features.chooseshop.domain.ChooseShopRepository
import com.thebestdiscountandroid.features.deals.data.DealsRepositoryImpl
import com.thebestdiscountandroid.features.deals.domain.DealsRepository
import com.thebestdiscountandroid.features.login.data.LoginRepositoryImpl
import com.thebestdiscountandroid.features.login.domain.LoginRepository
import com.thebestdiscountandroid.features.settings.domain.SettingsRepository
import com.thebestdiscountandroid.features.settings.data.SettingsRepositoryImpl
import com.thebestdiscountandroid.features.wish.data.WishListsRepositoryImpl
import com.thebestdiscountandroid.features.wish.domain.WishListsRepository
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit(@Named("MockInterceptor") mockInterceptor: Interceptor): Retrofit {

        //TODO baseUrl; with Kotlin its even easier
        // https://stackoverflow.com/questions/49975854/is-it-possible-to-use-multiple-baseurl-in-retrofit/53763430

        //TODO change it https://raw.githubusercontent.com/android10/Sample-Data/master/Android-CleanArchitecture-Kotlin/

        return Retrofit.Builder()
            .baseUrl("https://www.githubusercontent.com/v1/")
            .client(createClient(mockInterceptor))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(mockInterceptor: Interceptor): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
//            val loggingInterceptor =
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(mockInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    @Named("MockInterceptor")
    fun provideMockInterceptor(): Interceptor =
        MockInterceptor(application)

    @Provides
    @Singleton
    fun provideLoginRepository(dataSource: LoginRepositoryImpl): LoginRepository = dataSource

    @Provides
    @Singleton
    fun provideWishListRepository(dataSource: WishListsRepositoryImpl): WishListsRepository =
        dataSource

    @Provides
    @Singleton
    fun provideSettingsRepository(dataSource: SettingsRepositoryImpl): SettingsRepository =
        dataSource

    @Provides
    @Singleton
    fun provideChooseShopRepository(dataSource: ChooseShopRepositoryImpl): ChooseShopRepository =
        dataSource

    @Provides
    @Singleton
    fun provideDealsRepository(dataSource: DealsRepositoryImpl): DealsRepository =
        dataSource
}
