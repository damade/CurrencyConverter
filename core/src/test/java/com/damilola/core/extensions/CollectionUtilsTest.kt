package com.damilola.core.extensions

import com.damilola.core.ext.isNotNullOrEmpty
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class CollectionUtilsTest {

    @Suppress("unused")
    private fun paramsForIsNotNullOrEmpty() = arrayOf(
        arrayOf(null, false),
        arrayOf(emptyList<String>(), false),
        arrayOf(listOf(1,2,3), true),
    )

    @Test
    @Parameters(method = "paramsForIsNotNullOrEmpty")
    fun `when checking if a a collection is not null or empty, then the correct boolean is returned`(
        collection: List<Any>?,
        expectedResult: Boolean,
    ) {
        assertEquals(
            expectedResult,
            collection.isNotNullOrEmpty(),
        )
    }
}