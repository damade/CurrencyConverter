package com.damilola.core_android.ext

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

fun Spinner.addOnItemSelectedListener(
    onItemSelectedPositionAndText: (position: Int, text: String) -> Unit,
) {
    onItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val text: String = parent.getItemAtPosition(pos).toString()
                onItemSelectedPositionAndText(pos, text)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
}

