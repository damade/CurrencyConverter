package com.damade.lib_currency_search.data.mapper

import com.damade.lib_currency_search.data.model.ConversionEntity
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.model.Conversion
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConversionEntityMapperTest {
    private val mapper = ConversionEntityMapper()

    @Test
    fun mapFromEntity() {
        // given
        val entity: ConversionEntity = DummyData.conversionEntity
        val expectedConversionDomainData = DummyData.conversion
        // when
        val domain: Conversion = mapper.mapFromEntity(entity)
        // then
        assertThat(domain).isEqualTo(expectedConversionDomainData)
    }

    @Test
    fun mapToEntity() {
        // given
        val domain: Conversion = DummyData.conversion
        val expectedConversionEntityData = DummyData.conversionEntity
        // when
        val entity: ConversionEntity = mapper.mapToEntity(domain)
        // then
        assertThat(entity).isEqualTo(expectedConversionEntityData)
    }

}