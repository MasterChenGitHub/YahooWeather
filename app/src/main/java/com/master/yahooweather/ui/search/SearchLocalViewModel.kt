package com.master.yahooweather.ui.search

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.master.yahooweather.base.BaseViewModel
import com.master.yahooweather.base.Constants
import com.master.yahooweather.domain.usecase.SearchLocalCitiesUseCase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchLocalViewModel @Inject internal constructor(private val useCase: SearchLocalCitiesUseCase, private val pref: SharedPreferences) : BaseViewModel() {

    private val _searchParams: MutableLiveData<SearchLocalCitiesUseCase.SearchCitiesParams> = MutableLiveData()
    fun getSearchViewState() = searchViewState

    private val searchViewState: LiveData<SearchLocalViewState> = _searchParams.switchMap { params ->
        useCase.execute(params)
    }

    fun setSearchParams(params: SearchLocalCitiesUseCase.SearchCitiesParams) {
        if (_searchParams.value == params)
            return
        _searchParams.postValue(params)
    }

    fun saveCityNameToSharedPref(cityName: String): Single<String>? {
        return Single.create<String> {
            pref.edit().putString(Constants.CurrentCity.CITY_NAME, "$cityName,cn").apply()
            it.onSuccess("")
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}
