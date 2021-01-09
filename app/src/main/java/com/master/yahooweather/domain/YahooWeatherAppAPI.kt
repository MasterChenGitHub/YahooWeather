package com.master.yahooweather.domain

import com.master.yahooweather.domain.model.YahooForecastResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by MasterChen on 2020/12/29
 */
interface YahooWeatherAppAPI {

    @GET("forecastrss")
    fun getForecastByGeoCords(
        @Query("location")
        location:String
    ): Single<YahooForecastResponse>

    @GET("forecastrss")
    fun getCurrentByGeoCords(
        @Query("location")
        location:String
    ): Single<YahooForecastResponse>
}
