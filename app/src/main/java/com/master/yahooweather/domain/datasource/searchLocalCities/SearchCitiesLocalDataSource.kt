package com.master.yahooweather.domain.datasource.searchLocalCities

import android.util.Log
import androidx.lifecycle.LiveData
import com.master.yahooweather.db.dao.CitiesSearchDao
import com.master.yahooweather.db.entity.LocalCityEntity
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchCitiesLocalDataSource @Inject constructor(private val citiesForSearchDao: CitiesSearchDao) {

    fun getCityByName(cityName: String?): LiveData<List<LocalCityEntity>>
    {
        val l1: LiveData<List<LocalCityEntity>> =  citiesForSearchDao.getCityByName(cityName)
        Log.e("chen",l1.value?.size.toString())
        return l1
    }
}