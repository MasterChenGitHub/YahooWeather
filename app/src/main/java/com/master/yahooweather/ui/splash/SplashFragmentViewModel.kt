package com.master.yahooweather.ui.splash

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import com.master.yahooweather.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class SplashFragmentViewModel @ViewModelInject constructor(var sharedPreferences: SharedPreferences) : BaseViewModel() {
    var navigateDashboard = false
}
