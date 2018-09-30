package com.codezfox.exchangerates.rates

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.codezfox.exchangerates.R
import com.codezfox.exchangerates.data.Rate
import com.codezfox.exchangerates.databinding.ItemRateBinding

internal class RateAdapter : RecyclerView.Adapter<RateAdapter.RateViewHolder>() {

    var items: List<Rate> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        val binding = DataBindingUtil.inflate<ItemRateBinding>(
                LayoutInflater.from(parent.context), R.layout.item_rate, parent, false)

        return RateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal class RateViewHolder(val binding: ItemRateBinding) : RecyclerView.ViewHolder(binding.root)
}
