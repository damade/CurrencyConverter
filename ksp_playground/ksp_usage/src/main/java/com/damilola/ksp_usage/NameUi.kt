package com.damilola.ksp_usage

import com.damilola.annotation.fake_generator.GenerateFake

@GenerateFake
data class NameUi(val id: Long, val fullName: String, val age: Int, val prefix: String?)

@GenerateFake
internal data class RateUi(val rate: Double, val symbol: String, val isLatestRate: Boolean)

internal fun NameUi.getFullName() = fullName




object NameUiFakes {
    fun data(
        id: Long = 0,
        fullName: String = "",
        age: Int = 0,
        prefix: String? = null,
    ) = NameUi(
        id = id,
        fullName = fullName,
        age = age,
        prefix = prefix,
    )
}