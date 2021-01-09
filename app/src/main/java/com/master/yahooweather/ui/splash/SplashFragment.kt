package com.master.yahooweather.ui.splash

import android.graphics.Color
import androidx.navigation.fragment.findNavController
import com.master.yahooweather.R
import com.master.yahooweather.base.BaseFragment
import com.master.yahooweather.base.Constants
import com.master.yahooweather.databinding.FragmentSplashBinding
import com.master.yahooweather.di.Injectable
import com.master.yahooweather.utils.extensions.hide
import com.master.yahooweather.utils.extensions.show
import com.mikhaellopez.rxanimation.*
import io.reactivex.disposables.CompositeDisposable
import java.util.logging.Handler

/**
 * Created by MasterChen on 2020/12/29
 */
class SplashFragment : BaseFragment<SplashFragmentViewModel, FragmentSplashBinding>(R.layout.fragment_splash, SplashFragmentViewModel::class.java),
    Injectable {

    var disposable = CompositeDisposable()

    override fun init() {
        super.init()

        if (binding.viewModel?.sharedPreferences?.getString(Constants.CurrentCity.CITY_NAME, "").isNullOrEmpty()) {
            binding.buttonExplore.show()
            binding.viewModel?.navigateDashboard = false
        } else {
            binding.buttonExplore.hide()
            binding.viewModel?.navigateDashboard = true
        }

        binding.viewModel?.navigateDashboard?.let {
            android.os.Handler().postDelayed( {startSplashAnimation(it)},2000)
            }

        binding.buttonExplore.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }

        binding.rootView.setOnClickListener {
            binding.viewModel?.navigateDashboard?.let { it1 -> endSplashAnimation(it1) }
        }
    }

    private fun startSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    binding.imageViewBottomDrawable.translationY(500f),
                    binding.imageViewEllipse.fadeOut(0L),
                    binding.imageViewBottomDrawable.fadeOut(0L),
                    binding.imageViewBigCloud.translationX(-500F, 0L),
                    binding.imageViewSmallCloud.translationX(500f, 0L),
                    binding.imageViewBottomDrawableShadow.translationY(500f),
                    binding.imageViewMainCloud.fadeOut(0L),
                    binding.buttonExplore.fadeOut(0L),
                    binding.imageViewBottomDrawableShadow.fadeOut(0L)
                ),

                RxAnimation.together(
                    binding.imageViewBottomDrawable.fadeIn(1000L),
                    binding.imageViewBottomDrawable.translationY(-1f),
                    binding.imageViewBottomDrawableShadow.fadeIn(1250L),
                    binding.imageViewBottomDrawableShadow.translationY(-1f)
                ),

                RxAnimation.together(
                    binding.imageViewEllipse.fadeIn(1000L),
                    binding.imageViewEllipse.translationY(-50F, 1000L)
                ),

                RxAnimation.together(
                    binding.imageViewBigCloud.translationX(-15f, 1000L),
                    binding.imageViewSmallCloud.translationX(25f, 1000L)
                ),

                binding.imageViewMainCloud.fadeIn(500L),
                binding.buttonExplore.fadeIn(1000L)
            ).doOnTerminate {
                findNavController().graph.startDestination = R.id.yahooDashboardFragment // Little bit tricky solution :)
                if (navigateToDashboard)
                    endSplashAnimation(navigateToDashboard)
            }
                .subscribe()
        )
    }

    private fun endSplashAnimation(navigateToDashboard: Boolean) {
        disposable.add(
            RxAnimation.sequentially(
                RxAnimation.together(
                    binding.imageViewBottomDrawable.fadeOut(300L),
                    binding.imageViewBottomDrawable.translationY(100f),
                    binding.imageViewBottomDrawableShadow.fadeOut(300L),
                    binding.imageViewBottomDrawableShadow.translationY(100f)
                ),

                RxAnimation.together(
                    binding.imageViewEllipse.fadeOut(300L),
                    binding.imageViewEllipse.translationY(500F, 300L)
                ),

                RxAnimation.together(
                    binding.imageViewBigCloud.translationX(500f, 300L),
                    binding.imageViewSmallCloud.translationX(-500f, 300L)
                ),

                binding.imageViewMainCloud.fadeOut(300L),
                binding.buttonExplore.fadeOut(300L),
                binding.rootView.backgroundColor(
                    Color.parseColor("#5D50FE"),
                    Color.parseColor("#FFFFFF"),
                    duration = 750L
                )
            )
                .doOnTerminate {
                    findNavController().graph.startDestination = R.id.yahooDashboardFragment // Little bit tricky solution :)
                    if (navigateToDashboard)
                        navigate(R.id.action_splashFragment_to_yahooDashboardFragment)
                    else
                        navigate(R.id.action_splashFragment_to_searchLocalFragment)
                }
                .subscribe()

        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}