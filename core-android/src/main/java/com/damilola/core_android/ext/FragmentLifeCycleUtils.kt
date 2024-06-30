package com.damilola.core_android.ext

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T> Fragment.viewLifecycleLazy(initialise: () -> T): ReadOnlyProperty<Fragment, T> =
object : ReadOnlyProperty<Fragment, T>, DefaultLifecycleObserver {

    // A backing property to hold our value
    private var binding: T? = null

    private var viewLifecycleOwner: LifecycleOwner? = null

    init {
        // Observe the View Lifecycle of the Fragment
        this@viewLifecycleLazy
            .viewLifecycleOwnerLiveData
            .observe(this@viewLifecycleLazy, Observer { newLifecycleOwner ->
                viewLifecycleOwner
                    ?.lifecycle
                    ?.removeObserver(this)

                viewLifecycleOwner = newLifecycleOwner.also {
                    it.lifecycle.addObserver(this)
                }
            })
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        binding = null
    }

    override fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): T {
        // Return the backing property if it's set, or initialise
        return this.binding ?: initialise().also {
            this.binding = it
        }
    }
}

/**
 * An extension to bind and unbind a value based on the view lifecycle of a Fragment.
 * The binding will be unbound in onDestroyView.
 *
 * @throws IllegalStateException If the getter is invoked before the binding is set,
 *                               or after onDestroyView an exception is thrown.
 */
fun <T> Fragment.viewLifecycle(): ReadWriteProperty<Fragment, T> =
    object : ReadWriteProperty<Fragment, T>, DefaultLifecycleObserver {

        private var bindingObject: T? = null

        init {
            // Observe the view lifecycle of the Fragment.
            // The view lifecycle owner is null before onCreateView and after onDestroyView.
            // The observer is automatically removed after the onDestroy event.
            this@viewLifecycle
                .viewLifecycleOwnerLiveData
                .observe(this@viewLifecycle, Observer { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                })
        }

        override fun onDestroy(owner: LifecycleOwner) {
            bindingObject = null
        }

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T {
            return this.bindingObject ?: error("Called before onCreateView or after onDestroyView.")
        }

        override fun setValue(
            thisRef: Fragment,
            property: KProperty<*>,
            value: T
        ) {
            this.bindingObject = value
        }
    }