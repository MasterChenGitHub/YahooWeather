package com.master.yahooweather.ui.splash

import android.content.SharedPreferences
import com.master.yahooweather.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by MasterChen on 2020/12/29
 */
class SplashFragmentViewModel @Inject constructor(var sharedPreferences: SharedPreferences) : BaseViewModel() {
    var navigateDashboard = false
}
