package com.damilola.core_android.ext

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.damilola.core_android.R

enum class TransitionType {
    SIBLING, DETAIL, MODAL
}

inline fun <reified T : Fragment> FragmentManager.handleReplace(
    tag: String = T::class.java.name,
    addToBackStack: Boolean = false,
    @IdRes containerId: Int = R.id.fragment_container,
    transitionType: TransitionType? = null,
    crossinline newInstance: () -> T
) {
    beginTransaction().apply {
        transitionType?.let { setTransition(it) }
        replace(containerId, findFragmentByTag(tag) ?: newInstance.invoke(), tag)
        if (addToBackStack) {
            addToBackStack(null)
        }
        setReorderingAllowed(true)
        commitAllowingStateLoss()
    }
}

fun FragmentTransaction.setTransition(transitionType: TransitionType) {
    setCustomAnimations(
        when (transitionType) {
            TransitionType.SIBLING -> R.anim.fade_in
            TransitionType.DETAIL -> R.anim.slide_from_end
            TransitionType.MODAL -> R.anim.slide_from_bottom
        },
        R.anim.fade_out,
        R.anim.fade_in,
        when (transitionType) {
            TransitionType.SIBLING -> R.anim.fade_out
            TransitionType.DETAIL -> R.anim.slide_to_end
            TransitionType.MODAL -> R.anim.slide_to_bottom
        }
    )
}