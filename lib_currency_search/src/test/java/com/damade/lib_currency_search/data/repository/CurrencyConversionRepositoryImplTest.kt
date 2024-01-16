package com.damade.lib_currency_search.data.repository

import app.cash.turbine.test
import com.damade.lib_currency_search.data.fakes.FakeCurrencyConversionCache
import com.damade.lib_currency_search.data.fakes.FakeCurrencyConversionWithFlagsCache
import com.damade.lib_currency_search.data.fakes.FakeCurrencyRemote
import com.damade.lib_currency_search.data.fakes.FakeCurrencySymbolCache
import com.damade.lib_currency_search.data.mapper.ConversionEntityMapper
import com.damade.lib_currency_search.data.mapper.ConversionWithFlagsEntityMapper
import com.damade.lib_currency_search.data.mapper.SymbolEntityMapper
import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity
import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.model.Conversion
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.damade.lib_currency_search.domain.model.Symbol
import com.damilola.testutils.ERROR_MSG
import com.damilola.testutils.RemoteResponseType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CurrencyConversionRepositoryImplTest {

    private val fakeCurrencyConversionCache = FakeCurrencyConversionCache()
    private val fakeCurrencyRemote = FakeCurrencyRemote()
    private val fakeCurrencyConversionWithFlagsCache = FakeCurrencyConversionWithFlagsCache()
    private val fakeCurrencySymbolCache = FakeCurrencySymbolCache()
    private val conversionEntityMapper = ConversionEntityMapper()
    private val conversionWithFlagsEntityMapper = ConversionWithFlagsEntityMapper()
    private val symbolEntityMapper = SymbolEntityMapper()

    private val repository = CurrencyConversionRepositoryImpl(
        currencyRemote = fakeCurrencyRemote,
        currencyConversionCache = fakeCurrencyConversionCache,
        currencySymbolCache = fakeCurrencySymbolCache,
        conversionEntityMapper = conversionEntityMapper,
        symbolEntityMapper = symbolEntityMapper,
        currencyConversionWithFlagsCache = fakeCurrencyConversionWithFlagsCache,
        conversionWithFlagsEntityMapper = conversionWithFlagsEntityMapper
    )


    @Test
    fun `check that getSymbols returns data from remote when cache is empty`() =
        runTest {
            // given
            fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
            val symbolsEntity: List<SymbolEntity> = listOf(DummyData.symbolEntity)
            // when
            val symbols: Flow<List<Symbol>> =
                repository.getCurrencySymbol()
            // then
            assertThat(symbolsEntity).isEqualTo(
                symbolEntityMapper.mapFromDomainList(symbols.first())
            )
        }

    @Test
    fun `check that getSymbols returns data from cache when remote call fails`() =
        runTest {
            // given
            fakeCurrencyRemote.remoteResponseType = RemoteResponseType.FAILURE
            val symbolsEntity: List<SymbolEntity> = listOf(DummyData.symbolEntity)
            saveToSymbolDb()
            // when
            val symbols: Flow<List<Symbol>> =
                repository.getCurrencySymbol()
            // then
            assertThat(symbolsEntity).isEqualTo(
                symbolEntityMapper.mapFromDomainList(symbols.first())
            )
        }

    @Test
    fun `check that getSymbols returns an exception when remote call fails and cache info is null or empty`() =
        runTest {
            // given
            fakeCurrencyRemote.remoteResponseType = RemoteResponseType.FAILURE
            // when
            clearSymbolDb()
            val symbols: Flow<List<Symbol>> =
                repository.getCurrencySymbol()
            // then
            symbols.test {
                assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
            }
        }

    @Test
    fun `check that getSymbols saves data into cache when remote call succeeds`() =
        runTest {
            // given
            fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
            val expectedSymbolsEntity: List<SymbolEntity> = listOf(DummyData.symbolEntity)
            // when
            repository.getCurrencySymbol().collect()
            // then
            assertThat(fetchSymbolDb()).isEqualTo(
                expectedSymbolsEntity
            )

        }

    @Test
    fun `check that getConversionRate returns data when remote call succeeds`() = runTest {
        // given
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
        val conversionEntity: ConversionEntity = DummyData.conversionEntity
        // when
        val conversion: Flow<Conversion> =
            repository.getConvertedRate(DummyData.from, DummyData.to, DummyData.amount)
        // then
        assertThat(conversionEntity).isEqualTo(
            conversionEntityMapper.mapToEntity(conversion.first())
        )
    }

    @Test
    fun `check that getConversionRate Query is returned appropriately from remote`() = runTest {
        // when
        val conversion: Flow<Conversion> =
            repository.getConvertedRate(DummyData.from, DummyData.to, DummyData.amount)
        // then
        assertThat(DummyData.from).isEqualTo(conversion.first().from)
        assertThat(DummyData.to).isEqualTo(conversion.first().to)
        assertThat(DummyData.amount).isEqualTo(conversion.first().amount)

    }

    @Test
    fun `check that getConversionRate saves data into cache when remote call succeeds`() = runTest {
        // given
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
        val expectedConversionEntity = listOf(DummyData.conversionEntity)
        // when
        repository.getConvertedRate(DummyData.from, DummyData.to, DummyData.amount).collect()
        // then
        assertThat(fetchCurrencyConversionDb()).isEqualTo(
            expectedConversionEntity
        )

    }

    @Test
    fun `check that getConversionRate returns an exception when remote call fails `() = runTest {
        // given
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.FAILURE
        // when
        val getConversion = repository.getConvertedRate(DummyData.from, DummyData.to, DummyData.amount)
        // then
        getConversion.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }

    }

    @Test
    fun `check that getConversionWithFlagRate returns data when remote call succeeds`() = runTest {
        // given
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
        val conversionEntity: ConversionEntity = DummyData.conversionEntity
        // when
        val conversion: Flow<Conversion> =
            repository.getConvertedRateWithFlags(
                from = DummyData.from,
                fromCurrencyFlag = DummyData.fromCurrencyFlag,
                to = DummyData.to,
                toCurrencyFlag = DummyData.toCurrencyFlag,
                amount = DummyData.amount
            )
        // then
        assertThat(conversionEntity).isEqualTo(
            conversionEntityMapper.mapToEntity(conversion.first())
        )
    }

    @Test
    fun `check that getConversionWithFlagRate Query is returned appropriately from remote`() = runTest {
        // when
        val conversion: Flow<Conversion> =
            repository.getConvertedRateWithFlags(
                from = DummyData.from,
                fromCurrencyFlag = DummyData.fromCurrencyFlag,
                to = DummyData.to,
                toCurrencyFlag = DummyData.toCurrencyFlag,
                amount = DummyData.amount
            )
        // then
        assertThat(DummyData.from).isEqualTo(conversion.first().from)
        assertThat(DummyData.to).isEqualTo(conversion.first().to)
        assertThat(DummyData.amount).isEqualTo(conversion.first().amount)

    }

    @Test
    fun `check that getConversionWithFlagRate saves data into cache when remote call succeeds`() = runTest {
        // given
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
        val expectedConversionEntity = listOf(DummyData.conversionWithFlagsEntity)
        // when
        repository.getConvertedRateWithFlags(
            from = DummyData.from,
            fromCurrencyFlag = DummyData.fromCurrencyFlag,
            to = DummyData.to,
            toCurrencyFlag = DummyData.toCurrencyFlag,
            amount = DummyData.amount
        ).collect()
        // then
        assertThat(fetchCurrencyWithFlagsConversionDb()).isEqualTo(
            expectedConversionEntity
        )

    }

    @Test
    fun `check that getConversionWithFlagRate returns an exception when remote call fails `() = runTest {
        // given
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.FAILURE
        // when
        val getConversion = repository.getConvertedRateWithFlags(
            from = DummyData.from,
            fromCurrencyFlag = DummyData.fromCurrencyFlag,
            to = DummyData.to,
            toCurrencyFlag = DummyData.toCurrencyFlag,
            amount = DummyData.amount
        )
        // then
        getConversion.test {
            assertThat(awaitError()).hasMessageThat().isEqualTo(ERROR_MSG)
        }

    }

    @Test
    fun `check that getConversionWithFlagRate returns data from remote`() = runTest {
        fakeCurrencyRemote.remoteResponseType = RemoteResponseType.SUCCESS
        val conversionWithFlagsEntity: ConversionEntity = DummyData.conversionEntity
        val conversion: Flow<Conversion> =
            repository.getConvertedRateWithFlags(
                from = DummyData.from,
                fromCurrencyFlag = DummyData.fromCurrencyFlag,
                to = DummyData.to,
                toCurrencyFlag = DummyData.toCurrencyFlag,
                amount = DummyData.amount
            )
        assertThat(conversionWithFlagsEntity).isEqualTo(
            conversionEntityMapper.mapToEntity(conversion.first())
        )
    }

    @Test
    fun `check that getConversionRateHistory returns conversion rate data`() = runTest {
        saveToCurrencyConversionDb()
        val conversionRateHistoriesEntity: List<ConversionEntity> = listOf(DummyData.conversionEntity)
        val conversions: Flow<List<Conversion>> =
            repository.fetchConvertedRateHistory()
        assertThat(conversionRateHistoriesEntity).isEqualTo(
            conversionEntityMapper.mapFromDomainList(conversions.first())
        )
    }

    @Test
    fun `check that getConversionWithFlagsRateHistory returns conversion rate data`() = runTest {
        saveToCurrencyWithFlagsConversionDb()
        val conversionWithFlagsRateHistoriesEntity: List<ConversionWithFlagsEntity> =
            listOf(DummyData.conversionWithFlagsEntity)
        val conversions: Flow<List<ConversionWithFlags>> =
            repository.fetchConvertedRateWithFlagHistory()
        assertThat(conversionWithFlagsRateHistoriesEntity).isEqualTo(
            conversionWithFlagsEntityMapper.mapFromDomainList(conversions.first())
        )
    }

    @Test
    fun `check that clearConvertedRateHistory returns empty list`() = runTest {
        // given
        val expectedList = emptyList<ConversionEntity>()
        // when
        repository.clearConvertedRateHistory()
        // then
        assertThat(fetchCurrencyConversionDb()).isEqualTo(
            expectedList
        )
    }

    @Test
    fun `check that clearConvertedWithFlagsRateHistory returns empty list`() = runTest {
        // given
        val expectedList = emptyList<ConversionEntity>()
        // when
        repository.clearConvertedRateWithFlagHistory()
        // then
        assertThat(fetchCurrencyWithFlagsConversionDb()).isEqualTo(
            expectedList
        )
    }

    private suspend fun saveToCurrencyConversionDb() {
        fakeCurrencyConversionCache.saveCurrencyConversion(DummyData.conversionEntity)
    }

    private suspend fun saveToSymbolDb() {
        fakeCurrencySymbolCache.saveCurrencySymbol(listOf(DummyData.symbolEntity))
    }

    private suspend fun saveToCurrencyWithFlagsConversionDb() {
        fakeCurrencyConversionWithFlagsCache.saveCurrencyConversion(DummyData.conversionWithFlagsEntity)
    }

    private suspend fun fetchCurrencyConversionDb(): List<ConversionEntity> {
        return fakeCurrencyConversionCache.fetchCurrencyConversionHistory()
    }

    private suspend fun fetchSymbolDb(): List<SymbolEntity> {
        return fakeCurrencySymbolCache.fetchCurrencySymbol()
    }

    private suspend fun fetchCurrencyWithFlagsConversionDb(): List<ConversionWithFlagsEntity> {
        return fakeCurrencyConversionWithFlagsCache.fetchCurrencyConversionHistory()
    }

    private fun clearSymbolDb() {
        return fakeCurrencySymbolCache.clearCache()
    }

}
