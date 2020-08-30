package com.thebestdiscountandroid.features.deals.presentative

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.extension.*
import com.thebestdiscountandroid.core.navigation.Navigator
import com.thebestdiscountandroid.core.platform.BaseFragment
import com.thebestdiscountandroid.core.storage.PreferencesStorage
import com.thebestdiscountandroid.features.deals.presentative.recyclerview.DealView
import com.thebestdiscountandroid.features.deals.presentative.recyclerview.DealsAdapter
import kotlinx.android.synthetic.main.deals_fragment.*
import kotlinx.android.synthetic.main.empty_layout.emptyView
import javax.inject.Inject

class DealsFragment : BaseFragment() {

    companion object {
        const val DEALS_HEADER: String = "Top deals"
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var dealsAdapter: DealsAdapter

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    private lateinit var dealsViewModel: DealsViewModel

    private lateinit var optionsMenu: Menu

    override fun layoutId() = R.layout.deals_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        dealsViewModel = viewModel(viewModelFactory) {
            observe(deals, ::renderDeals)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_deals, menu)
        optionsMenu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.finish()
            return true
        }
        if (item.itemId == R.id.actionSearch) {
            return true
        }
        if (item.itemId == R.id.actionDone) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadDeals()
    }

    private fun initializeView() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.title = "Category Name"

        dealsRecyclerView.layoutManager = LinearLayoutManager(context)
        dealsRecyclerView.adapter = dealsAdapter
        dealsAdapter.shortClickListener = {
        }
        dealsAdapter.longClickListener = {
            dealsAdapter.selectDeal(it)
            if (dealsAdapter.areThereSelectedItems()) {
                changeOptionsMenuView(true)
            } else {
                changeOptionsMenuView(false)
            }
        }
    }

    private fun changeOptionsMenuView(itemsSelected: Boolean) {
        optionsMenu.findItem(R.id.actionDone).isVisible = itemsSelected
        optionsMenu.findItem(R.id.actionSearch).isVisible = !itemsSelected
    }

    private fun loadDeals() {
        dealsRecyclerView.visible()
        emptyView.invisible()
        showProgress()
        dealsViewModel.loadDeals(preferencesStorage.getUserId())
    }

    private fun renderDeals(dealItems: List<DealView>?) {
        if (dealItems != null) {
            dealsAdapter.collection = dealItems as ArrayList<DealView>
        } else {
            handleFailure(DealsFailure.ListNotAvailable())
        }
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is DealsFailure.ListNotAvailable -> renderFailure(R.string.failure_wish_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        dealsRecyclerView.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.failure_action_refresh, ::loadDeals)
    }
}