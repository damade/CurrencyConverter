package com.damilola.core.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.SelectBuilder
import kotlinx.coroutines.selects.select
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.cancellation.CancellationException

fun CoroutineScope.launchOrThrow(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit,
): Job = launch(context, start, block).also {
    check(!it.isCancelled) {
        "launch failed. Job is already cancelled"
    }
}


/*
  See: https://github.com/Kotlin/kotlinx.coroutines/issues/1498
 */
fun <A, B: Any, R> Flow<A>.withLatestFrom(other: Flow<B>, transform: suspend (A, B) -> R): Flow<R> = flow {
    coroutineScope {
        val latestB = AtomicReference<B?>()
        val outerScope = this
        launch {
            try {
                other.collect { latestB.set(it) }
            } catch(e: CancellationException) {
                outerScope.cancel(e) // cancel outer scope on cancellation exception, too
            }
        }
        collect { a: A ->
            latestB.get()?.let { b -> emit(transform(a, b)) }
        }
    }
}


/*
See https://github.com/Kotlin/kotlinx.coroutines/issues/2867
 */
suspend fun <R> race(vararg races: suspend () -> R): R {
    return channelFlow {
        for (race in races) {
            launch { send(race()) }
        }
    }.first()
}

/*
* Example is suspend fun main() {
    val result = __selectAndCancelRest__<String> {
        __async__ {
            delay(300)
            println("slow")
            "Slow Result"
        }
        __async__ {
            delay(200)
            println("fast")
            "Fast Result"
         }
    }
    println("Result=$result")
}
 */