package com.master.yahooweather.repo

import androidx.lifecycle.LiveData
import com.master.yahooweather.db.entity.LocalCityEntity
import com.master.yahooweather.domain.datasource.searchLocalCities.SearchCitiesLocalDataSource
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */

class SearchLocalCitiesRepository @Inject constructor(
    private val searchCitiesLocalDataSource: SearchCitiesLocalDataSource
) {

    fun loadCitiesByCityName(cityName: String?): LiveData<List<LocalCityEntity>> {
        return searchCitiesLocalDataSource.getCityByName(cityName)
    }
}
