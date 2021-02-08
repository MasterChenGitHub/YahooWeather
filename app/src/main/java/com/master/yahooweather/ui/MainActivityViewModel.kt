package com.master.yahooweather.ui

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import com.master.yahooweather.base.BaseViewModel

class MainActivityViewModel @ViewModelInject  constructor() : BaseViewModel() {
    var toolbarTitle: ObservableField<String> = ObservableField()
}