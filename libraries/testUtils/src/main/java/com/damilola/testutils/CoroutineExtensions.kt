package com.damilola.testutils

import com.google.common.truth.IterableSubject
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.TestScope
import org.junit.Assert
import org.junit.function.ThrowingRunnable


inline fun <reified T> IterableSubject.containsElements(vararg instance: T) {
    containsExactlyElementsIn(instance).inOrder()
}

/**
 * Source: https://github.com/Ezike/StarWarsSearch-MVI/blob/main/libraries/testUtils/src/main/java/com/ezike/tobenna/starwarssearch/testutils/Extensions.kt
 * */
@OptIn(ExperimentalCoroutinesApi::class)
inline fun <reified T : Throwable> TestScope.assertThrows(
    crossinline runnable: suspend () -> Unit
): T {
    val throwingRunnable = ThrowingRunnable {
        val job: Deferred<Unit> = async {
            runnable()
        }
        job.getCompletionExceptionOrNull()?.run { throw this }
        job.cancel()

    }
    return Assert.assertThrows(T::class.java, throwingRunnable)
}
