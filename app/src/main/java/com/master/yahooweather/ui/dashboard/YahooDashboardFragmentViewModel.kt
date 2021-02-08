package com.master.yahooweather.ui.dashboard

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.master.yahooweather.base.BaseViewModel
import com.master.yahooweather.domain.usecase.CurrentWeatherYahooUseCase
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class YahooDashboardFragmentViewModel @ViewModelInject  constructor(private val currentWeatherUseCase: CurrentWeatherYahooUseCase, var sharedPreferences: SharedPreferences) : BaseViewModel() {

    private val _currentWeatherParams: MutableLiveData<CurrentWeatherYahooUseCase.CurrentWeatherParams> = MutableLiveData()

    fun getCurrentWeatherViewState() = currentWeatherViewState

    private val currentWeatherViewState: LiveData<YahooForecastViewState> = _currentWeatherParams.switchMap { params ->
        currentWeatherUseCase.execute(params)
    }


    fun setCurrentWeatherParams(params: CurrentWeatherYahooUseCase.CurrentWeatherParams) {
        if (_currentWeatherParams.value == params)
            return
        _currentWeatherParams.postValue(params)
    }
}
