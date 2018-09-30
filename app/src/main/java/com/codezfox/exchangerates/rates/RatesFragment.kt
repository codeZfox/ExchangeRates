package com.codezfox.exchangerates.rates

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codezfox.exchangerates.R
import com.codezfox.exchangerates.data.Rate
import com.codezfox.exchangerates.databinding.FragmentRatesBinding
import com.codezfox.exchangerates.di.Injectable
import com.codezfox.exchangerates.obtainViewModel
import javax.inject.Inject

class RatesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var ratesViewModel: RatesViewModel
    private lateinit var binding: FragmentRatesBinding

    private val adapter = RateAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ratesViewModel = obtainViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rates, container, false)
        binding.setViewmodel(ratesViewModel)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        ratesViewModel.ratesListObservable.observe(this, Observer<List<Rate>> { items ->
            if (items != null) {
                adapter.items = items
                adapter.notifyDataSetChanged()
            }
        })

        return binding.getRoot()
    }

}