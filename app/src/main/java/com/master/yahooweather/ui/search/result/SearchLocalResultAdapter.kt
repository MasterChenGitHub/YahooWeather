package com.master.yahooweather.ui.search.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.master.yahooweather.base.BaseAdapter
import com.master.yahooweather.databinding.ItemSearchLocalResultBinding
import com.master.yahooweather.db.entity.LocalCityEntity

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchLocalResultAdapter(private val callBack: (LocalCityEntity) -> Unit) : BaseAdapter<LocalCityEntity>(localDiffCallback) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val mBinding = ItemSearchLocalResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewModel = SearchLocalResultItemViewModel()
        mBinding.viewModel = viewModel
        mBinding.rootView.setOnClickListener {
            mBinding.viewModel?.item?.get()?.let {
                callBack.invoke(it)
            }
        }
        return mBinding
    }

    override fun bind(binding: ViewDataBinding, position: Int) {
        (binding as ItemSearchLocalResultBinding).viewModel?.item?.set(getItem(position))
        binding.executePendingBindings()
    }
}

val localDiffCallback = object : DiffUtil.ItemCallback<LocalCityEntity>() {
    override fun areContentsTheSame(oldItem: LocalCityEntity, newItem: LocalCityEntity): Boolean =
        oldItem == newItem

    override fun areItemsTheSame(oldItem: LocalCityEntity, newItem: LocalCityEntity): Boolean =
        oldItem.cityName == newItem.cityName
}
