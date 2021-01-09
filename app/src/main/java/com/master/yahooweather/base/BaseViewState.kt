package com.master.yahooweather.base

import com.master.yahooweather.utils.domain.Status

/**
 * Created by MasterChen on 2020/12/28
 */
open class BaseViewState(val baseStatus: Status, val baseError: String?) {
    fun isLoading() = baseStatus == Status.LOADING
    fun getErrorMessage() = baseError
    fun shouldShowErrorMessage() = baseError != null
}
