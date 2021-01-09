package com.master.yahooweather.domain.datasource.currentWeather

import com.master.yahooweather.domain.YahooWeatherAppAPI
import com.master.yahooweather.domain.model.YahooForecastResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class CurrentWeatherYahooRemoteDataSource @Inject constructor(private val api: YahooWeatherAppAPI) {
    fun getCurrentWeatherByGeoCords(cityName: String): Single<YahooForecastResponse> = api.getCurrentByGeoCords(cityName)
}
