package com.master.yahooweather.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.master.yahooweather.base.BaseAdapter
import com.master.yahooweather.databinding.ItemForecastYahooBinding
import com.master.yahooweather.domain.model.YahooForecast

/**
 * Created by MasterChen on 2020/12/29
 */
class YahooForecastAdapter(private val callBack: (YahooForecast, View, View) -> Unit) : BaseAdapter<YahooForecast>(diffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemForecastYahooBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = YahooForecastItemViewModel()
        mBinding.viewModel = viewModel

        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {

                callBack.invoke(
                    it,
                    mBinding.imageViewForecastIcon,
                    mBinding.textViewDayOfWeek
                )
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemForecastYahooBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val diffCallback = object : DiffUtil.ItemCallback<YahooForecast>() {
    override fun areContentsTheSame(oldItem: YahooForecast, newItem: YahooForecast): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: YahooForecast, newItem: YahooForecast): Boolean =
        oldItem.date == newItem.date
}
