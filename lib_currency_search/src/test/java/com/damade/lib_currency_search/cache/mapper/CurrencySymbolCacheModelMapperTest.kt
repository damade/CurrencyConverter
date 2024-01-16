package com.damade.lib_currency_search.cache.mapper

import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.data_generator.CacheDummyData
import com.damilola.cache.model.SymbolCacheModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CurrencySymbolCacheModelMapperTest {
    private val mapper = CurrencySymbolCacheModelMapper()

    @Test
    fun mapToCacheModel() {
        // given
        val symbolEntity: SymbolEntity = CacheDummyData.symbolEntity
        val expectedSymbolCacheData = CacheDummyData.symbolCacheModel
        // when
        val symbolCacheModel = mapper.mapToModel(entity = symbolEntity)
        // then
        assertThat(symbolCacheModel).isEqualTo(expectedSymbolCacheData)
    }

    @Test
    fun mapToEntity() {
        // given
        val symbolCacheModel: SymbolCacheModel = CacheDummyData.symbolCacheModel
        val expectedSymbolEntityData = CacheDummyData.symbolEntity
        // when
        val entity = mapper.mapToEntity(model = symbolCacheModel)
        // then
        assertThat(entity).isEqualTo(expectedSymbolEntityData)
    }

    @Test
    fun mapToListOfCacheModel() {
        // given
        val symbolEntity: List<SymbolEntity> = listOf(CacheDummyData.symbolEntity)
        val expectedSymbolCacheData = listOf(CacheDummyData.symbolCacheModel)
        // when
        val symbolCacheModels = mapper.mapListToModel(entity = symbolEntity)
        // then
        assertThat(symbolCacheModels).isEqualTo(expectedSymbolCacheData)
    }

    @Test
    fun mapToListEntity() {
        // given
        val symbolCacheModel: List<SymbolCacheModel> = listOf(CacheDummyData.symbolCacheModel)
        val expectedSymbolEntityData = listOf(CacheDummyData.symbolEntity)
        // when
        val entity = mapper.mapListToEntity(model = symbolCacheModel)
        // then
        assertThat(entity).isEqualTo(expectedSymbolEntityData)
    }
}