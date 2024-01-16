package com.damade.lib_currency_search.domain.usecases.conversion

import app.cash.turbine.test
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionWithFlagRate
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
class GetConversionWithFlagRateTest {

    private val repository = FakeCurrencyConversionRepository()

    private val getConversionWithFlagRate = GetConversionWithFlagRate(
        repository,
        UnconfinedTestDispatcher()
    )

    @Test
    fun `check that getConversionWithFlagRate returns conversion result`() = runTest {
        // given
        repository.conversionWithFlagsResponseType = ResponseType.DATA
        // when
        val conversion: Conversion = getConversionWithFlagRate(DummyData.queryWithFlags).first()
        // then
        assertThat(conversion).isEqualTo(DummyData.conversion)
    }

    @Test
    fun `check that getConversionWithFlagRate does not return null`() = runTest {
        // given
        repository.conversionWithFlagsResponseType = ResponseType.DATA
        // when
        val conversion: Conversion = getConversionWithFlagRate(DummyData.queryWithFlags).first()
        // then
        assertThat(conversion).isNotNull()
    }

    @Test
    fun `check that getConversionWithFlagRate returns error if call fails`() = runTest {
        // given
        repository.conversionWithFlagsResponseType = ResponseType.ERROR
        // when
        val getConversionWithFlag = getConversionWithFlagRate(DummyData.queryWithFlags)
        // then
        getConversionWithFlag.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }
    }

    @Test(expected = NoParamsException::class)
    fun `check that getConversionWithFlagRate throws NoParams exce ption when called without params`() =
        runTest {
            getConversionWithFlagRate().collect()
        }
}
