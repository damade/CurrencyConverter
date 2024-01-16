package com.damade.lib_currency_search.domain.usecases.conversion

import app.cash.turbine.test
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.usecase.conversion.GetConversionHistory
import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.ResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetConversionHistoryTest {

    private val repository = FakeCurrencyConversionRepository()

    private val getConversionHistory = GetConversionHistory(
        repository,
        UnconfinedTestDispatcher()
    )

    @Test
    fun `check that getConversionHistory returns a list of conversions done previously `() = runTest {
        // given
        repository.conversionHistoryResponseType = ResponseType.DATA
        // when
        val conversion: List<Conversion> = getConversionHistory().first()
        //then
        assertThat(conversion).isEqualTo(DummyData.conversionHistory)
    }

    @Test
    fun `check that getConversionHistory does not return null`() = runTest {
        // given
        repository.conversionHistoryResponseType = ResponseType.DATA
        // when
        val conversion: List<Conversion> = getConversionHistory().first()
        // then
        assertThat(conversion).isNotNull()
    }


    @Test
    fun `check that getConversionHistory returns an empty if local source is empty`() = runTest {
        // given
        repository.conversionHistoryResponseType = ResponseType.EMPTY
        // when
        val conversion: List<Conversion> = getConversionHistory().first()
        // then
        assertThat(conversion).isEmpty()
    }


    @Test
    fun `check that getConversionRate returns error if call fails`() = runTest {
        // given
        repository.conversionHistoryResponseType = ResponseType.ERROR
        // when
        val conversionHistory = getConversionHistory()
        //then
        conversionHistory.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }
    }
}
