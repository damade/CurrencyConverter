package com.damilola.core_android.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.damilola.core.objects.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable

fun <T1 : Any, T2 : Any, R> combineLatest(
    liveData1: LiveData<T1>,
    liveData2: LiveData<T2>,
    combiner: (T1, T2) -> R,
): LiveData<R> = MediatorLiveData<R>().apply {
    var first: T1? = null
    var second: T2? = null

    fun updateValueIfNeeded() {
        value = combiner(
            first ?: return,
            second ?: return,
        )?.takeIf { it != value } ?: return
    }

    addSource(liveData1) {
        first = it
        updateValueIfNeeded()
    }
    addSource(liveData2) {
        second = it
        updateValueIfNeeded()
    }


}

class NonNullMediatorLiveData<T> : MediatorLiveData<T>()

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

fun <T> LiveData<Event<T>>.eventObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
            if (it.hasNotBeenHandled) {
                val result = it.getContentIfNotHandled()
                result?.let {
                    observer(result)
                }
            }else{
                val result = it.peekContent()
                result?.let {
                    observer(result)
                }
//                if (result != null) {
//                    observer(result)
//                }
            }
    })
}


fun <T> LiveData<T>.nonNull(): NonNullMediatorLiveData<T> {
    val mediator: NonNullMediatorLiveData<T> = NonNullMediatorLiveData()
    mediator.addSource(this, Observer { it?.let { mediator.value = it } })
    return mediator
}
fun <T> NonNullMediatorLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

fun <T : Any> Observable<T>.subscribeOnMain(block: (T) -> Unit): Disposable =
    observeOn(AndroidSchedulers.mainThread()).subscribe { block(it) }



