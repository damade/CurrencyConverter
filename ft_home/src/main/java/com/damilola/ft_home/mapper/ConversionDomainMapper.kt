package com.damilola.ft_home.mapper

import com.damade.lib_currency_search.domain.model.Conversion
import com.damilola.core.ext.orZero
import com.damilola.core.mapper.DomainMapper
import com.damilola.core_android.R
import com.damilola.core_android.utils.resource_providers.ParamString
import com.damilola.ft_home.model.ConversionUi
import javax.inject.Inject

internal class ConversionDomainMapper @Inject constructor() :
    DomainMapper<Conversion, ConversionUi> {

    override fun mapFromDomain(domain: Conversion): ConversionUi {
        with(domain) {
            return ConversionUi(
                result = result,
                rate = rate,
                from = from,
                to = to,
                amount = amount,
                resultInfo = ParamString(
                    res = R.string.rate_result,
                    rate.orZero(), from, to
                ).takeIf { rate != null }
            )
        }
    }

    override fun mapToDomain(uiModel: ConversionUi): Conversion {
        with(uiModel) {
            return Conversion(
                result = result,
                rate = rate,
                from = from,
                to = to,
                amount = amount
            )
        }
    }
}