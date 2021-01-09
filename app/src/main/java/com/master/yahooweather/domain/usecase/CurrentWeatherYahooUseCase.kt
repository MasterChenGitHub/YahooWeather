package com.master.yahooweather.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.master.yahooweather.base.Constants
import com.master.yahooweather.db.entity.YahooForecastEntity
import com.master.yahooweather.repo.CurrentWeatherYahooRepository
import com.master.yahooweather.ui.dashboard.YahooForecastViewState
import com.master.yahooweather.utils.domain.Resource
import com.master.yahooweather.utils.UseCaseLiveData
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class CurrentWeatherYahooUseCase @Inject internal constructor(private val repository: CurrentWeatherYahooRepository) : UseCaseLiveData<YahooForecastViewState, CurrentWeatherYahooUseCase.CurrentWeatherParams, CurrentWeatherYahooRepository>() {

    override fun getRepository(): CurrentWeatherYahooRepository {
        return repository
    }

    override fun buildUseCaseObservable(params: CurrentWeatherParams?): LiveData<YahooForecastViewState> {
        return repository.loadCurrentWeatherByGeoCords(
            params?.cityName?:"",
            params?.fetchRequired ?: false,
            units = params?.units ?: Constants.Coords.METRIC
        ).map {
            onCurrentWeatherResultReady(it)
        }
    }

    private fun onCurrentWeatherResultReady(resource: Resource<YahooForecastEntity>): YahooForecastViewState {
        return YahooForecastViewState(
            status = resource.status,
            error = resource.message,
            data = resource.data
        )
    }

    class CurrentWeatherParams(
        val cityName: String = "",
        val fetchRequired: Boolean,
        val units: String
    ) : Params()
}
