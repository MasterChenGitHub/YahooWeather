package com.master.yahooweather.ui.search

import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.master.yahooweather.R
import com.master.yahooweather.base.BaseFragment
import com.master.yahooweather.databinding.FragmentSearchLocalBinding
import com.master.yahooweather.db.entity.LocalCityEntity
import com.master.yahooweather.di.Injectable
import com.master.yahooweather.domain.usecase.SearchLocalCitiesUseCase
import com.master.yahooweather.ui.MainActivity
import com.master.yahooweather.ui.search.result.SearchLocalResultAdapter
import com.master.yahooweather.utils.domain.extensions.observeWith
import com.master.yahooweather.utils.domain.tryCatch
import com.master.yahooweather.utils.extensions.hideKeyboard
import java.util.*

/**
 * Created by MasterChen on 2020/12/29
 */
class SearchLocalFragment : BaseFragment<SearchLocalViewModel, FragmentSearchLocalBinding>(R.layout.fragment_search_local, SearchLocalViewModel::class.java),
    Injectable {

    override fun init() {
        super.init()
        initSearchResultsAdapter()
        initSearchView()

        binding.viewModel?.getSearchViewState()?.observeWith(
            viewLifecycleOwner
        ) {
            binding.viewState = it
            it.data?.let { results -> initSearchResultsRecyclerView(results) }
        }
    }

    private fun initSearchView() {
        val searchEditText: EditText = binding.searchView.findViewById(R.id.search_src_text)
        activity?.applicationContext?.let { ContextCompat.getColor(it, R.color.mainTextColor) }
            ?.let { searchEditText.setTextColor(it) }
        activity?.applicationContext?.let { ContextCompat.getColor(it, android.R.color.darker_gray) }
            ?.let { searchEditText.setHintTextColor(it) }
        binding.searchView.isActivated = true
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.isIconified = false

        val searchViewSearchIcon = binding.searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchViewSearchIcon.setImageResource(R.drawable.ic_search)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String): Boolean {
                searchByCityName(newText)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchByCityName(newText)
                return true
            }
        })
    }

    private fun searchByCityName(newText: String?) {

        if (newText!=null&&newText.isNotEmpty() && newText.count() > 0) {
            binding.viewModel?.setSearchParams(SearchLocalCitiesUseCase.SearchCitiesParams(newText))
        }else{
            initSearchResultsRecyclerView(Collections.emptyList())
        }
    }

    private fun initSearchResultsAdapter() {
        val adapter = SearchLocalResultAdapter { item ->
            item.allPinyin?.let {

                binding.viewModel?.saveCityNameToSharedPref(it.removeSuffix("shi"))
                    ?.subscribe { _, _ ->

                        tryCatch(
                            tryBlock = {
                                binding.searchView.hideKeyboard((activity as MainActivity))
                            }
                        )

                        findNavController().navigate(R.id.action_searchLocalFragment_to_yahooDashboardFragment)
                    }
            }
        }

        binding.recyclerViewSearchResults.adapter = adapter
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initSearchResultsRecyclerView(list: List<LocalCityEntity>) {
        (binding.recyclerViewSearchResults.adapter as SearchLocalResultAdapter).submitList(list.distinctBy { it.allFirstPinyin }.sortedBy { it.firstPinyin })
    }
}
