package com.master.yahooweather.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.master.yahooweather.db.entity.YahooForecastEntity

/**
 * Created by MasterChen on 2020/12/29
 */

@Dao
interface YahooWeatherDao {

    @Query("SELECT * FROM Weather limit 1")
    fun getCurrentWeather(): LiveData<YahooForecastEntity>

    @Query("SELECT * FROM Weather ")
    fun getForecast(): LiveData<YahooForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentWeather(currentWeatherEntity: YahooForecastEntity)

    @Transaction
    fun deleteAndInsert(currentWeatherEntity: YahooForecastEntity) {
        deleteCurrentWeather()
        insertCurrentWeather(currentWeatherEntity)
    }

    @Query("DELETE FROM Weather")
    fun deleteCurrentWeather()

    @Query("Select count(*) from Weather")
    fun getCount(): Int
}
