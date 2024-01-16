package com.damade.lib_currency_search.data.mapper

import com.damade.lib_currency_search.data.model.SymbolEntity
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.model.Symbol
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SymbolEntityMapperTest {
    private val mapper = SymbolEntityMapper()

    @Test
    fun mapFromEntity() {
        val entity: SymbolEntity = DummyData.symbolEntity
        val domain: Symbol = mapper.mapFromEntity(entity)
        assertThat(entity.code).isEqualTo(domain.currencyCode)
        assertThat(entity.description).isEqualTo(domain.currencyDescription)
    }

    @Test
    fun mapToEntity() {
        val domain: Symbol = DummyData.symbol
        val entity: SymbolEntity = mapper.mapToEntity(domain)
        assertThat(domain.currencyCode).isEqualTo(entity.code)
        assertThat(domain.currencyDescription).isEqualTo(entity.description)
    }

}