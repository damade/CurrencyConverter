package com.damade.lib_currency_search.domain.usecases.conversion

import app.cash.turbine.test
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionRate
import com.damilola.core.exception.NoParamsException
import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.ResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetConversionRateTest {

    private val repository = FakeCurrencyConversionRepository()

    private val getConversionRate = GetConversionRate(
        repository,
        UnconfinedTestDispatcher()
    )

    @Test
    fun `check that getConversionRate returns conversion result`() = runTest {
        // given
        repository.conversionResponseType = ResponseType.DATA
        // when
        val conversion: Conversion = getConversionRate(DummyData.query).first()
        // then
        assertThat(conversion).isEqualTo(DummyData.conversion)
    }

    @Test
    fun `check that getConversionRate does not return null`() = runTest {
        // given
        repository.conversionResponseType = ResponseType.DATA
        // when
        val conversion: Conversion = getConversionRate(DummyData.query).first()
        // then
        assertThat(conversion).isNotNull()
    }

    @Test
    fun `check that getConversionRate returns error if call fails`() = runTest {
        // given
        repository.conversionResponseType = ResponseType.ERROR
        // when
        val getConversion = getConversionRate(DummyData.query)
        // then
        getConversion.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }
    }

    @Test(expected = NoParamsException::class)
    fun `check that getConversionRate throws NoParams exce ption when called without params`() =
        runTest {
            getConversionRate().collect()
        }
}
