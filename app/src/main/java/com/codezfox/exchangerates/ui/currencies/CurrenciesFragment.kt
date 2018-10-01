package com.codezfox.exchangerates.ui.currencies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codezfox.exchangerates.R
import com.codezfox.exchangerates.data.models.Currency
import com.codezfox.exchangerates.databinding.FragmentCurrenciesBinding
import com.codezfox.exchangerates.di.Injectable
import com.codezfox.exchangerates.viewmodels.currencies.CurrenciesViewModel
import com.codezfox.exchangerates.utils.ErrorCause
import com.codezfox.exchangerates.utils.obtainViewModel
import org.jetbrains.anko.alert
import javax.inject.Inject


class CurrenciesFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var ratesViewModel: CurrenciesViewModel
    private lateinit var binding: FragmentCurrenciesBinding

    private val adapter = CurrencyAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        ratesViewModel = obtainViewModel(viewModelFactory)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currencies, container, false)
        binding.setViewmodel(ratesViewModel)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        val callback = DragItemTouchHelperCallback(adapter).also {
            it.onSelectedChanged = { isSelected ->
                binding.swipeRefreshLayout.setEnabled(!isSelected)
            }
        }
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.recyclerView)


        binding.swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(activity!!, R.color.colorPrimaryDark)
        )

        ratesViewModel.items.observe(this, Observer<List<Currency>> { items ->
            if (items != null) {
                adapter.setCurrencyItems(items)
            }
        })

        ratesViewModel.alert.observe(this, Observer<ErrorCause> { errorCause ->
            if (errorCause != null) {

                activity?.alert {

                    errorCause.getMessage(activity!!)?.let {
                        title = it
                    }

                    errorCause.localizedMessage?.let {
                        message = it
                    }

                    positiveButton(android.R.string.ok)
                    show()
                }
            }
        })

        return binding.getRoot()
    }

}