package com.app.exchanger.di

import com.app.exchanger.data.repository.CurrencyExchangeRepositoryImpl
import com.app.exchanger.data.source.CurrencyExchangeApi
import com.app.exchanger.domain.provider.ExchangeRulesProvider
import com.app.exchanger.domain.repository.CurrencyExchangeRepository
import com.app.exchanger.domain.usecase.ExchangeUseCase
import com.app.exchanger.domain.usecase.GetExchangeRateUseCase
import com.app.exchanger.domain.usecase.GetExchangeRatesUseCase
import com.app.exchanger.domain.usecase.UpdateBalanceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyExchangeRepository(currencyExchangeApi: CurrencyExchangeApi): CurrencyExchangeRepository =
        CurrencyExchangeRepositoryImpl(currencyExchangeApi)

    @Provides
    @Singleton
    fun provideExchangeRatesUseCase(currencyExchangeRepository: CurrencyExchangeRepository) =
        GetExchangeRatesUseCase(currencyExchangeRepository)

    @Provides
    @Singleton
    fun provideExchangeUseCase(getExchangeRateUseCase: GetExchangeRateUseCase) =
        ExchangeUseCase(ExchangeRulesProvider(), getExchangeRateUseCase)

    @Provides
    @Singleton
    fun provideExchangeRateUseCase() = GetExchangeRateUseCase()

    @Provides
    @Singleton
    fun provideUpdateBalanceUseCase() = UpdateBalanceUseCase()
}
