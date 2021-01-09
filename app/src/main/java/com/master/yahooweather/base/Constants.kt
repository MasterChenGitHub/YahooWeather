package com.master.yahooweather.base

/**
 * Created by MasterChen on 2020/12/28
 */
object Constants {

    object NetworkService {
        const val RATE_LIMITER_TYPE = "data"
    }

    object YahooWeatherNetworkService {
        val APP_ID = "rJDQlhKg"
        val CONSUMER_KEY = "dj0yJmk9cGFNT3ptQ2xVY3VHJmQ9WVdrOWNrcEVVV3hvUzJjbWNHbzlNQT09JnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWNk"
        val CONSUMER_SECRET = "018ab6e58fe19dd86a697cb0d6d3740b628333d5"
        val URL = "https://weather-ydn-yql.media.yahoo.com/"
    }


    object Coords {
        const val LAT = "lat"
        const val LON = "lon"
        const val METRIC = "metric"
    }

    object CurrentCity {
        const val CITY_NAME = "city_name"
    }
}
