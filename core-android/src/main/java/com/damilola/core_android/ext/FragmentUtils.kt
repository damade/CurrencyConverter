package com.damilola.core_android.ext

import android.widget.Toast
import androidx.fragment.app.Fragment
import java.security.acl.Owner
import kotlin.reflect.KProperty

class FragmentDelegate(private var fragment: Fragment) {
    operator fun getValue(thisRef: Owner, property: KProperty<*>): Fragment {
        return fragment
    }

    operator fun setValue(thisRef: Owner, property: KProperty<*>, value: Any?) {
        if (value is Fragment) {
            fragment = value
        }
    }
}

fun Fragment.showShortToast(string: String) {
    Toast.makeText(this.requireContext(), string, Toast.LENGTH_SHORT).show()
}