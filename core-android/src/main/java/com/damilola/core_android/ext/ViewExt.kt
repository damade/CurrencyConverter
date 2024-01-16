package com.damilola.core_android.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.damilola.core_android.R
import com.damilola.core_android.databinding.ErrorStateBinding
import com.damilola.core_android.utils.ui_providers.RVEmptyObserver
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.button.MaterialButton

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}

fun Fragment.onBackPress(onBackPressed: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        onBackPressed = onBackPressed
    )
}

fun AutoCompleteTextView.showText(text: String){
    this.setText(text,false)
    this.setTextColor(resources.getColor(R.color.app_black))
    this.setSelection(this.text.count())
}

fun Fragment.setPanSoftInput() {
    requireActivity().window?.setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
    )
}

fun Fragment.setResizeInput() {
    requireActivity().window?.setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
    )
}



fun RecyclerView.bindObserver(view: View){
    val newAdapter =  this.adapter
    val observer = RVEmptyObserver(this, view)
    newAdapter?.registerAdapterDataObserver(observer)
    this.adapter = newAdapter
}

fun ShimmerFrameLayout.stopAnim(){
    this.stopShimmer()
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.startAnim(){
    this.startShimmer()
    this.visibility = View.VISIBLE
}

fun ErrorStateBinding.showErrorMessage(errorEntered: String){
    this.root.visibility = View.VISIBLE
    this.errorMessage.text = errorEntered
}

fun EditText.afterTextChanged(button: MaterialButton) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if (s.toString().trim().isEmpty()) {
                // set text to Not typing
                button.setBackgroundColor(resources.getColor(R.color.app_diable_color))
            } else {
                // set text to typing
                button.setBackgroundColor(resources.getColor(R.color.app_green))
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            button.setBackgroundColor(resources.getColor(R.color.app_green))
        }

        override fun afterTextChanged(editable: Editable?) {
            if (editable.toString().trim().isEmpty()) {
                // set text to Stopped typing
                button.setBackgroundColor(resources.getColor(R.color.app_diable_color))
            }
        }
    })
}

fun MaterialButton.setShowProgress(showProgress: Boolean?) {
    icon = if (showProgress == true) {
        CircularProgressDrawable(context!!).apply {
            setStyle(CircularProgressDrawable.DEFAULT)
            setColorSchemeColors(ContextCompat.getColor(context!!, R.color.white))
            start()
        }
    } else null
    if (icon != null) { // callback to redraw button icon
        iconGravity = MaterialButton.ICON_GRAVITY_END
        icon.callback = object : Drawable.Callback {
            override fun unscheduleDrawable(who: Drawable, what: Runnable) {
            }

            override fun invalidateDrawable(who: Drawable) {
                this@setShowProgress.invalidate()
            }

            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
            }
        }
    }
    else{
        icon = context?.let { ContextCompat.getDrawable(it, R.drawable.ic_button_forward)}
        iconGravity = MaterialButton.ICON_GRAVITY_END
        iconTint = ContextCompat.getColorStateList(context, R.color.white)
    }
}

inline var View.show: Boolean
    get() = visibility == View.VISIBLE
    set(shouldShow) {
        visibility = if (shouldShow) View.VISIBLE else View.INVISIBLE
    }

inline val EditText.lazyText: () -> String
    get() = { this.text.trim().toString() }

