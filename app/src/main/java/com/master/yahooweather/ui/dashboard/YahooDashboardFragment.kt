package com.master.yahooweather.ui.dashboard

import android.transition.TransitionInflater
import androidx.core.app.ActivityCompat.postponeEnterTransition
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import androidx.recyclerview.widget.LinearLayoutManager
import com.master.yahooweather.R
import com.master.yahooweather.base.BaseFragment
import com.master.yahooweather.base.Constants
import com.master.yahooweather.databinding.FragmentDashboardYahooBinding
import com.master.yahooweather.di.Injectable
import com.master.yahooweather.domain.model.YahooForecast
import com.master.yahooweather.domain.usecase.CurrentWeatherYahooUseCase
import com.master.yahooweather.ui.MainActivity
import com.master.yahooweather.utils.domain.extensions.observeWith
import com.master.yahooweather.utils.extensions.isNetworkAvailable

/**
 * Created by MasterChen on 2020/12/29
 */
class YahooDashboardFragment : BaseFragment<YahooDashboardFragmentViewModel, FragmentDashboardYahooBinding>(
    R.layout.fragment_dashboard_yahoo, YahooDashboardFragmentViewModel::class.java), Injectable {

    override fun init() {
        super.init()
        initForecastAdapter()
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        val cityName: String? = binding.viewModel?.sharedPreferences?.getString(Constants.CurrentCity.CITY_NAME, "hangzhou,cn")

        if (cityName?.isNotEmpty() == true) {
            (activity as MainActivity).viewModel.toolbarTitle.set(cityName)
            binding.viewModel?.setCurrentWeatherParams(CurrentWeatherYahooUseCase.CurrentWeatherParams(cityName, isNetworkAvailable(requireContext()), Constants.Coords.METRIC))
        }

        binding.viewModel?.getCurrentWeatherViewState()?.observeWith(
            viewLifecycleOwner
        ) {

            with(binding) {
                containerForecast.viewState = it

                it.data?.forecasts?.let { fs -> initForecast(fs) }
            }
        }
    }

    private fun initForecastAdapter() {
        val adapter = YahooForecastAdapter { item, forecastIcon, dayOfWeek -> }

        binding.recyclerForecast.adapter = adapter
        binding.recyclerForecast.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        postponeEnterTransition()
        binding.recyclerForecast.viewTreeObserver
            .addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
    }

    private fun initForecast(list: List<YahooForecast?>) {
        if( list.isNotEmpty()){
            (binding.recyclerForecast.adapter as YahooForecastAdapter).submitList(list.subList(1,list.size-1))
        }
    }
}
