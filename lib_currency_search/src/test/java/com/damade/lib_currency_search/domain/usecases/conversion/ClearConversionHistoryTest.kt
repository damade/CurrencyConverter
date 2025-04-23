package com.damade.lib_currency_search.domain.usecases.conversion

import com.damade.lib_currency_search.domain.fakes.FakeCurrencyConversionRepository
import com.damade.lib_currency_search.domain.usecase.conversion.ClearConversionHistory
import com.damilola.testutils.ResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.jvm.Throws

@OptIn(ExperimentalCoroutinesApi::class)
class ClearConversionHistoryTest {

    private val repository = FakeCurrencyConversionRepository()

    private val clearConversionHistory = ClearConversionHistory(
        repository
    )

    @Test
    fun `when clearConversionHistory succeed, then it returns a unit`() = runTest {
        // given
        repository.clearConversionHistoryResponseType = ResponseType.SUCCESS
        // when
        val clearHistory = clearConversionHistory.execute()
        // then
        assertThat(clearHistory).isEqualTo(Unit)
    }

    @Test(expected = IOException::class)
    fun `when clearConversionHistory fails, then an exception is thrown`() = runTest {
        // given
        repository.clearConversionHistoryResponseType = ResponseType.ERROR
        // when
        val clearHistory = clearConversionHistory.execute()
        // then
        assertThat(clearHistory).isEqualTo(Unit)
    }

}
