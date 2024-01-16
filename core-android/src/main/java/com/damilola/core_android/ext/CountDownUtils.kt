package com.damilola.core_android.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * An extension to bind and unbind a value based on the view lifecycle of a Fragment.
 * The binding will be unbound in onDestroyView.
 *
 * @throws IllegalStateException If the getter is invoked before the binding is set,
 *                               or after onDestroyView an exception is thrown.
 */
fun <T> Fragment.timerLifecycle(): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        private var countObject: T? = null

        init {
            // Observe the view lifecycle of the Fragment.
            // The view lifecycle owner is null before onCreateView and after onDestroyView.
            // The observer is automatically removed after the onDestroy event.
            this@timerLifecycle
                .viewLifecycleOwnerLiveData
                .observe(this@timerLifecycle, Observer { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                })
        }

        override fun onDestroy(owner: LifecycleOwner) {
           countObject = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            return this.countObject ?: error("Called before onCreateView or after onDestroyView.")
        }

        override fun setValue(
            thisRef: Fragment,
            property: KProperty<*>,
            value: T
        ) {
            this.countObject = value
        }
    }

