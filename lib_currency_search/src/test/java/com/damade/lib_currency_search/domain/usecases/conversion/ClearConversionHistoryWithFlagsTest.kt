package com.damade.lib_currency_search.domain.usecases.conversion

import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.usecase.conversion.ClearConversionWithFlagHistory
import com.damilola.testutils.ResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class ClearConversionHistoryWithFlagsTest {

    private val repository = FakeCurrencyConversionRepository()

    private val clearConversionWithFlagHistory = ClearConversionWithFlagHistory(
        repository
    )

    @Test
    fun `when clearConversionWithFlagsHistory succeed, it returns a unit`() = runTest {
        // given
        repository.clearConversionHistoryWithFlagsResponseType = ResponseType.SUCCESS
        // when
        val clearHistory = clearConversionWithFlagHistory.execute()
        // then
        assertThat(clearHistory).isEqualTo(Unit)
    }

    @Test(expected = IOException::class)
    fun `when clearConversionWithFlagsHistory fails, an exception is thrown`() = runTest {
        // given
        repository.clearConversionHistoryWithFlagsResponseType = ResponseType.ERROR
        // when
        val clearHistory = clearConversionWithFlagHistory.execute()
    }

}
