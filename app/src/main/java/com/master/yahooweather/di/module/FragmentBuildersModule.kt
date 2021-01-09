package com.master.yahooweather.di.module

import com.master.yahooweather.ui.dashboard.YahooDashboardFragment
import com.master.yahooweather.ui.search.SearchLocalFragment
import com.master.yahooweather.ui.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by MasterChen on 2020/12/29
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchLocalFragment(): SearchLocalFragment

    @ContributesAndroidInjector
    abstract fun contributeYahooDashboardFragment(): YahooDashboardFragment
}