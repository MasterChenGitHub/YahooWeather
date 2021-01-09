package com.master.yahooweather.utils

import androidx.room.TypeConverter
import com.master.yahooweather.domain.model.YahooForecast
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

/**
 * Created by MasterChen on 2020-12-12
 */

object DataConverter {
    @TypeConverter
    @JvmStatic
    fun yahooForecastStringToList(data: String?): List<YahooForecast>? {
        if (data == null) {
            return emptyList()
        }

        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, YahooForecast::class.java)
        val adapter = moshi.adapter<List<YahooForecast>>(type)
        return adapter.fromJson(data)
    }

    @TypeConverter
    @JvmStatic
    fun yahooForecastListToString(objects: List<YahooForecast>): String {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, YahooForecast::class.java)
        val adapter = moshi.adapter<List<YahooForecast>>(type)
        return adapter.toJson(objects)
    }


}
