package com.damilola.ft_home.presentation.homeScreen.view_render

import android.widget.ArrayAdapter
import com.damilola.core.ext.isNotNullOrEmpty
import com.damilola.ft_home.R
import com.damilola.ft_home.databinding.FragmentHomeBinding
import com.damilola.ft_home.model.SymbolUi

internal fun FragmentHomeBinding.renderSpinner(symbols: List<SymbolUi>?) {
    if (symbols.isNotNullOrEmpty() && symbols != null) {
        val spinnerDataList: ArrayList<String> = ArrayList()
        for (eachData in symbols) {
            spinnerDataList.add(eachData.currencyCode)
        }
        val spinnerDataArray: Array<String> = spinnerDataList.toTypedArray()

        ArrayAdapter(
            this@renderSpinner.root.context,
            android.R.layout.simple_dropdown_item_1line,
            spinnerDataArray
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.drop_down_layout)
            toSpinner.adapter = adapter
            fromSpinner.adapter = adapter
        }
    }
}

internal fun FragmentHomeBinding.renderDefaultSpinner() {
    ArrayAdapter.createFromResource(
        this@renderDefaultSpinner.root.context,
        com.damilola.core_android.R.array.from_currencies,
        android.R.layout.simple_dropdown_item_1line
    ).also { adapter ->
        adapter.setDropDownViewResource(R.layout.drop_down_layout)
        toSpinner.adapter = adapter
        fromSpinner.adapter = adapter
    }
}


