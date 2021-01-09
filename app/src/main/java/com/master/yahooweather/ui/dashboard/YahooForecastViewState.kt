package com.master.yahooweather.ui.dashboard

import com.master.yahooweather.base.BaseViewState
import com.master.yahooweather.db.entity.YahooForecastEntity
import com.master.yahooweather.utils.domain.Status

/**
 * Created by MasterChen on 2020/12/29
 */
class YahooForecastViewState (
    val status: Status,
    val error: String? = null,
    val data: YahooForecastEntity? = null
) : BaseViewState(status, error) {
    fun getForecast() = data
}
