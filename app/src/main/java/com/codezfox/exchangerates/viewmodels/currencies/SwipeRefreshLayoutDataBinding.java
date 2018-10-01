package com.codezfox.exchangerates.viewmodels.currencies;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

public class SwipeRefreshLayoutDataBinding {

    /**
     * Reloads the data when the pull-to-refresh is triggered.
     * <p>
     * Creates the {@code android:onRefresh} for a {@link SwipeRefreshLayout}.
     */
    @BindingAdapter("android:onRefresh")
    public static void setSwipeRefreshLayoutOnRefreshListener(SwipeRefreshLayout view, final CurrenciesViewModel viewModel) {
        view.setOnRefreshListener(viewModel::load);
    }

}
