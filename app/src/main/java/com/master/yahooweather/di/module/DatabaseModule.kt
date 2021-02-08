package com.master.yahooweather.di.module

import android.app.Application
import androidx.room.Room
import com.master.yahooweather.db.CityDatabase
import com.master.yahooweather.db.YahooWeatherDatabase
import com.master.yahooweather.db.dao.CitiesSearchDao
import com.master.yahooweather.db.dao.YahooWeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by MasterChen on 2020/12/29
 */

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun getLocalCityDatabase(context: Application): CityDatabase {
        return Room.databaseBuilder(context, CityDatabase::class.java, "City-DB")
            .createFromAsset("databases/city.db")
            .build()
    }


    @Singleton
    @Provides
    fun provideCitiesSearchDao(db: CityDatabase): CitiesSearchDao {
        return db.citiesSearchDao()
    }


    @Singleton
    @Provides
    fun getYahooWeatherDatabase(context: Application): YahooWeatherDatabase {
        return Room.databaseBuilder(
            context,
            YahooWeatherDatabase::class.java, "WeatherYahoo-DB"
        ).build()
    }


    @Singleton
    @Provides
    fun provideYahooWeatherDao(db: YahooWeatherDatabase): YahooWeatherDao {
        return db.currentWeatherDao()
    }

}
