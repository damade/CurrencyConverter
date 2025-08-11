package com.damilola.ksp_usage

import com.damilola.annotation.fake_generator.FakeIt

@FakeIt
data class DataUi(val id: Long, val fullName: String, val age: Int, val prefix: String?)
