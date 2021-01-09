package com.master.yahooweather.ui.search.result

import androidx.databinding.ObservableField
import com.master.yahooweather.base.BaseViewModel
import com.master.yahooweather.db.entity.LocalCityEntity
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchLocalResultItemViewModel @Inject internal constructor() : BaseViewModel() {
    var item = ObservableField<LocalCityEntity>()
}
