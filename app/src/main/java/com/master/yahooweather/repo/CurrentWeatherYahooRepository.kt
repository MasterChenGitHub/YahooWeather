package com.master.yahooweather.repo

import androidx.lifecycle.LiveData
import com.master.yahooweather.base.Constants.NetworkService.RATE_LIMITER_TYPE
import com.master.yahooweather.db.entity.YahooForecastEntity
import com.master.yahooweather.domain.datasource.currentWeather.CurrentWeatherYahooLocalDataSource
import com.master.yahooweather.domain.datasource.currentWeather.CurrentWeatherYahooRemoteDataSource
import com.master.yahooweather.domain.model.YahooForecastResponse
import com.master.yahooweather.utils.domain.RateLimiter
import com.master.yahooweather.utils.domain.Resource
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class CurrentWeatherYahooRepository @Inject constructor(
    private val currentWeatherRemoteDataSource: CurrentWeatherYahooRemoteDataSource,
    private val currentWeatherLocalDataSource: CurrentWeatherYahooLocalDataSource
) {

    private val currentWeatherRateLimit = RateLimiter<String>(30, TimeUnit.SECONDS)

    fun loadCurrentWeatherByGeoCords(cityName:String, fetchRequired: Boolean, units: String): LiveData<Resource<YahooForecastEntity>> {
        return object : NetworkBoundResource<YahooForecastEntity, YahooForecastResponse>() {
            override fun saveCallResult(item: YahooForecastResponse) = currentWeatherLocalDataSource.insertCurrentWeather(item)

            override fun shouldFetch(data: YahooForecastEntity?): Boolean = fetchRequired

            override fun loadFromDb(): LiveData<YahooForecastEntity> = currentWeatherLocalDataSource.getCurrentWeather()

            override fun createCall(): Single<YahooForecastResponse> = currentWeatherRemoteDataSource.getCurrentWeatherByGeoCords(cityName)

            override fun onFetchFailed() = currentWeatherRateLimit.reset(RATE_LIMITER_TYPE)
        }.asLiveData
    }
}
