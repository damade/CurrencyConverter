package com.damilola.ft_home.presentation.historyScreen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damilola.core.ext.commaSeparator
import com.damilola.core_android.utils.ui_providers.DefaultDiffUtilItemCallback
import com.damilola.ft_home.databinding.ItemConversionHistoryBinding
import com.damilola.ft_home.model.ConversionUi

internal class ConversionAdapter : ListAdapter<ConversionUi, ConversionAdapter.ConversionViewHolder>(
    DefaultDiffUtilItemCallback()
) {

    inner class ConversionViewHolder(private val binding: ItemConversionHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun onBindView(item: ConversionUi) {
            binding.fromCurrency.text = item.from
            binding.fromAmount.text = item.amount.toString()
            binding.toCurrency.text = item.to
            binding.toAmount.text = commaSeparator(item.result)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionViewHolder {
        return ConversionViewHolder(
            binding = ItemConversionHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(holder: ConversionViewHolder, position: Int) {
        holder.onBindView(getItem(position))
    }
}