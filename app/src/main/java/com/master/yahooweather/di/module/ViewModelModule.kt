package com.master.yahooweather.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.master.yahooweather.di.ViewModelFactory
import com.master.yahooweather.di.key.ViewModelKey
import com.master.yahooweather.ui.MainActivityViewModel
import com.master.yahooweather.ui.dashboard.YahooDashboardFragmentViewModel
import com.master.yahooweather.ui.search.SearchLocalViewModel
import com.master.yahooweather.ui.search.result.SearchLocalResultItemViewModel
import com.master.yahooweather.ui.splash.SplashFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

/**
 * Created by MasterChen on 2020/12/29
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class ViewModelModule {
//
//    @Binds
//    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(SplashFragmentViewModel::class)
    abstract fun provideSplashFragmentViewModel(splashFragmentViewModel: SplashFragmentViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel


    @IntoMap
    @Binds
    @ViewModelKey(SearchLocalViewModel::class)
    abstract fun provideSearchLocalViewModel(searchViewModel: SearchLocalViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(SearchLocalResultItemViewModel::class)
    abstract fun provideSearchLocalResultItemViewModel(searchResultItemViewModel: SearchLocalResultItemViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(YahooDashboardFragmentViewModel::class)
    abstract fun provideYahooDashboardFragmentViewModel(searchResultItemViewModel: YahooDashboardFragmentViewModel): ViewModel

}
