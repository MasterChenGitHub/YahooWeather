package com.master.yahooweather.domain.datasource.currentWeather

import com.master.yahooweather.db.dao.YahooWeatherDao
import com.master.yahooweather.db.entity.YahooForecastEntity
import com.master.yahooweather.domain.model.YahooForecastResponse
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class CurrentWeatherYahooLocalDataSource @Inject constructor(private val currentWeatherDao: YahooWeatherDao) {
    fun getCurrentWeather() = currentWeatherDao.getCurrentWeather()

    fun insertCurrentWeather(currentWeather: YahooForecastResponse) = currentWeatherDao.deleteAndInsert(YahooForecastEntity(currentWeather))
}
