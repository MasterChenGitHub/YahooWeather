package com.master.yahooweather.ui.search

import com.master.yahooweather.base.BaseViewState
import com.master.yahooweather.db.entity.LocalCityEntity
import com.master.yahooweather.utils.domain.Status

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchLocalViewState(
    val status: Status,
    val error: String? = null,
    val data: List<LocalCityEntity>? = null
) : BaseViewState(status, error) {
    fun getSearchResult() = data
}