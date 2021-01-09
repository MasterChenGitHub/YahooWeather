package com.master.yahooweather.ui.dashboard

import androidx.databinding.ObservableField
import com.master.yahooweather.base.BaseViewModel
import com.master.yahooweather.domain.model.YahooForecast
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class YahooForecastItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<YahooForecast>()
}
