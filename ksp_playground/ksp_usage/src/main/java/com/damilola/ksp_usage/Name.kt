package com.damilola.ksp_usage

import com.damilola.annotation.fake_generator.GenerateFake

@GenerateFake
data class Name(val id: Long, val fullName: String, val age: Int, val prefix: String?)

fun Name.hereWego() {
    fake()
}