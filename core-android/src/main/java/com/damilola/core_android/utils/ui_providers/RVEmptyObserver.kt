package com.damilola.core_android.utils.ui_providers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import dagger.hilt.android.scopes.FragmentScoped

@FragmentScoped
class RVEmptyObserver(eRecyclerView: RecyclerView, ev: View?) : AdapterDataObserver() {
    private val emptyView: View? = ev
    private val recyclerView: RecyclerView? = eRecyclerView

    private fun checkIfEmpty() {
        if (emptyView != null && recyclerView?.adapter != null) {
            val emptyViewVisible = recyclerView.adapter!!.itemCount == 0
            emptyView.visibility = if (emptyViewVisible) View.VISIBLE else View.GONE
            recyclerView.visibility = if (emptyViewVisible) View.GONE else View.VISIBLE
        }
    }

    override fun onChanged() {
        checkIfEmpty()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        checkIfEmpty()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        checkIfEmpty()
    }

    init {
        checkIfEmpty()
    }
}

