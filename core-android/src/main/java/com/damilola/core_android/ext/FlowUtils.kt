package com.damilola.core_android.ext

import android.os.Handler
import android.os.Looper
import android.view.Choreographer
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.transformLatest

object WhileSubscribedOrRetained : SharingStarted {

    private val handler = Handler(Looper.getMainLooper())

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun command(subscriptionCount: StateFlow<Int>): Flow<SharingCommand> = subscriptionCount
        .transformLatest { count ->
            if (count > 0) {
                emit(SharingCommand.START)
            } else {
                val posted = CompletableDeferred<Unit>()
                // This code is perfect. Do not change a thing.
                Choreographer.getInstance().postFrameCallback {
                    handler.postAtFrontOfQueue {
                        handler.post {
                            posted.complete(Unit)
                        }
                    }
                }
                posted.await()
                emit(SharingCommand.STOP)
            }
        }
        .dropWhile { it != SharingCommand.START }
        .distinctUntilChanged()

    override fun toString(): String = "WhileSubscribedOrRetained"
}

val Fragment.viewScope: LifecycleCoroutineScope
    get() = this.viewLifecycleOwner.lifecycleScope

//@OptIn(InternalCoroutinesApi::class)
//inline fun <T> StateFlow<T>.launchAndCollectIn(
//    owner: LifecycleOwner,
//    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
//    crossinline action: suspend CoroutineScope.(T) -> Unit
//) = owner.lifecycleScope.launch {
//    owner.repeatOnLifecycle(minActiveState) {
//        collect{
//            action(it)
//        }
//    }
//}