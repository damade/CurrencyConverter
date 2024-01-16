package com.damade.lib_currency_search.data.mapper

import com.damade.lib_currency_search.data.model.ConversionWithFlagsEntity
import com.damade.lib_currency_search.data_generator.DummyData
import com.damade.lib_currency_search.domain.model.ConversionWithFlags
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConversionWithFlagsEntityMapperTest {
    private val mapper = ConversionWithFlagsEntityMapper()

    @Test
    fun mapFromEntity() {
        // given
        val entity: ConversionWithFlagsEntity = DummyData.conversionWithFlagsEntity
        val expectedConversionWithFlagDomainData = DummyData.gbpToNgnConversionWithFlags
        // when
        val domain: ConversionWithFlags = mapper.mapFromEntity(entity)
        // then
        assertThat(domain).isEqualTo(expectedConversionWithFlagDomainData)
    }

    @Test
    fun mapToEntity() {
        // given
        val domain: ConversionWithFlags = DummyData.gbpToNgnConversionWithFlags
        val expectedConversionEntityData = DummyData.conversionWithFlagsEntity
        // when
        val entity: ConversionWithFlagsEntity = mapper.mapToEntity(domain)
        // then
        assertThat(entity).isEqualTo(expectedConversionEntityData)
    }

}