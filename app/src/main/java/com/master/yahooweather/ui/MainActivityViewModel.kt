package com.master.yahooweather.ui

import androidx.databinding.ObservableField
import com.master.yahooweather.base.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject internal constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}