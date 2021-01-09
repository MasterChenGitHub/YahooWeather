package com.master.yahooweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.master.yahooweather.db.dao.YahooWeatherDao
import com.master.yahooweather.db.entity.YahooForecastEntity
import com.master.yahooweather.utils.DataConverter

/**
 * Created by MasterChen on 2020/12/29
 */
@Database(
    entities = [
        YahooForecastEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class YahooWeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): YahooWeatherDao

}