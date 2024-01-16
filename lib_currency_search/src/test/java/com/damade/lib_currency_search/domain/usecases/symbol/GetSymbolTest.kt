package com.damade.lib_currency_search.domain.usecases.symbol

import app.cash.turbine.test
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.model.Symbol
import com.damade.lib_currency_search.domain.usecase.symbol.GetSymbol
import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.ResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetSymbolTest {

    private val repository = FakeCurrencyConversionRepository()


    private val getSymbols = GetSymbol(
        repository,
        UnconfinedTestDispatcher()
    )

    @Test
    fun `check that getSymbols returns symbol list`() = runTest {
        // given
        repository.symbolResponseType = ResponseType.DATA
        // when
        val symbols: List<Symbol> = getSymbols().first()
        // then
        assertThat(symbols.size).isEqualTo(1)
        assertThat(symbols.first()).isEqualTo(DummyData.symbol)
    }

    @Test
    fun `check that getSymbols returns empty`() = runTest {
        // given
        repository.symbolResponseType = ResponseType.EMPTY
        // when
        val symbols = getSymbols()
        //then
        symbols.collect { listOfSymbols ->
            assertThat(listOfSymbols).isEmpty()
        }
    }

    @Test
    fun `check that getSymbols returns error if call fails`() = runTest {
        //given
        repository.symbolResponseType = ResponseType.ERROR
        // when
        val symbols = getSymbols()
        // then
        symbols.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }

    }

}