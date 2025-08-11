package com.damilola.ksp_usage

import com.damilola.annotation.fake_generator.FakeIt
import com.damilola.annotation.fake_generator.GenerateFake

@FakeIt
data class DataUi(
    val id: Long,
    val fullName: String,
    val age: Int,
    val prefix: String?,
)

@GenerateFake
data class MyUi(val id: Long, val fullName: String, val age: Int, val prefix: String?)
