package com.damilola.ft_home.presentation.historyScreen.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.damilola.core.ext.commaSeparator
import com.damilola.ft_home.R
import com.damilola.ft_home.model.ConversionUi

class ConversionAdapter (private val items: List<ConversionUi>, context: Context) :
    RecyclerView.Adapter<ConversionAdapter.ViewHolder>() {

    private val mContext = context
    private val data = items

    class ViewHolder(private val view: View, private val item : List<ConversionUi>) : RecyclerView.ViewHolder(view) {
        private val fromCurrencyTV: TextView = view.findViewById(R.id.from_currency)
        private val fromAmountTV: TextView = view.findViewById(R.id.from_amount)
        private val toCurrencyTV: TextView = view.findViewById(R.id.to_currency)
        private val toAmountTV: TextView = view.findViewById(R.id.to_amount)

        @SuppressLint("SetTextI18n")
        fun onBindView(item: ConversionUi) {
            fromCurrencyTV.text = item.from
            fromAmountTV.text = item.amount.toString()
            toCurrencyTV.text = item.to
            toAmountTV.text = commaSeparator(item.result)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(mContext)
                    .inflate(R.layout.item_conversion_history, parent, false),
                items )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindView(data[position])
    }

    override fun getItemCount(): Int {
        return data.count()
    }
}