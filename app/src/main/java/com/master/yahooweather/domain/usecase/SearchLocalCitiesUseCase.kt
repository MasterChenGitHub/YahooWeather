package com.master.yahooweather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.master.yahooweather.db.entity.LocalCityEntity
import com.master.yahooweather.repo.SearchLocalCitiesRepository
import com.master.yahooweather.utils.UseCaseLiveData
import com.master.yahooweather.utils.domain.Resource
import com.master.yahooweather.ui.search.SearchLocalViewState
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchLocalCitiesUseCase @Inject internal constructor(private val repository: SearchLocalCitiesRepository) : UseCaseLiveData<SearchLocalViewState, SearchLocalCitiesUseCase.SearchCitiesParams, SearchLocalCitiesRepository>() {

    override fun getRepository(): SearchLocalCitiesRepository = repository

    override fun buildUseCaseObservable(params: SearchCitiesParams?): LiveData<SearchLocalViewState> {
        return repository.loadCitiesByCityName(
            cityName = params?.city ?: ""
        ).map {
            onSearchResultReady(Resource.success(it))
        }
    }

    private fun onSearchResultReady(resource: Resource<List<LocalCityEntity>>): SearchLocalViewState {
        return SearchLocalViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class SearchCitiesParams(
        val city: String = ""
    ) : Params()
}
