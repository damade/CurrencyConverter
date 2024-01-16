package com.damade.lib_currency_search.domain.usecases.conversion

import app.cash.turbine.test
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionWithFlagHistory
import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.ResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetConversionWithFlagHistoryTest {

    private val repository = FakeCurrencyConversionRepository()

    private val getConversionWithFlagHistory = GetConversionWithFlagHistory(
        repository,
        UnconfinedTestDispatcher()
    )

    @Test
    fun `check that getConversionWithFlagHistory returns a list of conversions with flag `() = runTest {
        // given
        repository.conversionWithFlagsHistoryResponseType = ResponseType.DATA
        // when
        val conversionWithFlagHistory: List<ConversionWithFlags> = getConversionWithFlagHistory().first()
        //then
        assertThat(conversionWithFlagHistory).isEqualTo(DummyData.conversionWithFlagsHistory)
    }

    @Test
    fun `check that getConversionWithFlagHistory does not return null`() = runTest {
        // given
        repository.conversionWithFlagsHistoryResponseType = ResponseType.DATA
        // when
        val conversionWithFlagHistory: List<ConversionWithFlags> = getConversionWithFlagHistory().first()
        // then
        assertThat(conversionWithFlagHistory).isNotNull()
    }


    @Test
    fun `check that getConversionHistory returns an empty if local source is empty`() = runTest {
        // given
        repository.conversionWithFlagsHistoryResponseType = ResponseType.EMPTY
        // when
        val conversionWithFlagHistory: List<ConversionWithFlags> = getConversionWithFlagHistory().first()
        // then
        assertThat(conversionWithFlagHistory).isEmpty()
    }


    @Test
    fun `check that getConversionRate returns error if call fails`() = runTest {
        // given
        repository.conversionWithFlagsHistoryResponseType = ResponseType.ERROR
        // when
        val conversionWithFlagHistoryHistory = getConversionWithFlagHistory()
        //then
        conversionWithFlagHistoryHistory.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }
    }
}
