package com.master.yahooweather.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Created by MasterChen on 2020/12/28
 */
abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>() :
    AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        setupBindingLifecycleOwner()
    }

    /**
     *
     *  You need override this method.
     *  And you need to set viewModel to binding: binding.viewModel = viewModel
     *
     */

    abstract fun initViewModel()

    private fun setupBindingLifecycleOwner() {
        binding.lifecycleOwner = this
    }
}
