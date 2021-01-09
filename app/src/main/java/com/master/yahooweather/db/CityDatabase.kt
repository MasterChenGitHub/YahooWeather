package com.master.yahooweather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.master.yahooweather.db.dao.CitiesSearchDao
import com.master.yahooweather.db.entity.LocalCityEntity
import com.master.yahooweather.utils.DataConverter

/**
 * Created by MasterChen on 2020/12/29
 */
@Database(
    entities = [
        LocalCityEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class CityDatabase : RoomDatabase() {

    abstract fun citiesSearchDao(): CitiesSearchDao
}
