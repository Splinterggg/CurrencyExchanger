package dev.sdex.exchanger.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.app.exchanger.domain.model.Balance
import com.app.exchanger.domain.model.ExchangeTransaction
import com.app.exchanger.domain.provider.ExchangeRulesProvider
import com.app.exchanger.domain.usecase.ExchangeUseCase
import com.app.exchanger.domain.usecase.GetExchangeRateUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class ExchangeUseCaseTest {

    private lateinit var useCase: ExchangeUseCase
    private lateinit var repository: FakeCurrencyExchangeRepository

    @BeforeEach
    fun setUp() {
        repository = FakeCurrencyExchangeRepository()
        useCase = ExchangeUseCase(
            ExchangeRulesProvider(),
            GetExchangeRateUseCase(),
        )
    }

    @Test
    fun `Exchange EUR to USD`(): Unit = runBlocking {
        val transactionResult = useCase(
            exchangeRates = repository.getExchangeRates().first().data!!.rates,
            listOf(
                Balance(
                    currency = "EUR",
                    amount = BigDecimal("1000")
                )
            ),
            ExchangeTransaction(
                sellCurrency = "EUR",
                buyCurrency = "USD",
                amount = BigDecimal("100"),
            )
        )
        assertThat(transactionResult.change.sellBalanceChange)
            .isEqualTo(BigDecimal("-100"))
        assertThat(transactionResult.change.buyBalanceChange)
            .isEqualTo(BigDecimal("110.0"))
        assertThat(transactionResult.transactionFee).isEqualTo(BigDecimal.ZERO)
    }
}