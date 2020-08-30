package com.thebestdiscountandroid.features.wish.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.thebestdiscountandroid.R
import com.thebestdiscountandroid.core.exception.Failure
import com.thebestdiscountandroid.core.exception.Failure.NetworkConnection
import com.thebestdiscountandroid.core.exception.Failure.ServerError
import com.thebestdiscountandroid.core.extension.*
import com.thebestdiscountandroid.core.navigation.Navigator
import com.thebestdiscountandroid.core.platform.BaseFragment
import com.thebestdiscountandroid.core.storage.PreferencesStorage
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishCategoryView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishListsAdapter
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishProductView
import com.thebestdiscountandroid.features.wish.presentation.expandablerecyclerview.WishView
import kotlinx.android.synthetic.main.empty_layout.*
import kotlinx.android.synthetic.main.wish_fragment.*
import javax.inject.Inject


class WishListsFragment : BaseFragment() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var wishListsAdapter: WishListsAdapter

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    private lateinit var wishListsViewModel: WishListsViewModel

    private lateinit var optionsMenu: Menu

    private lateinit var drawerLayout: DrawerLayout

    override fun layoutId() = R.layout.wish_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        wishListsViewModel = viewModel(viewModelFactory) {
            observe(wishLists, ::renderWishLists)
            failure(failure, ::handleFailure)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_wish_lists, menu)
        optionsMenu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START)
            return true
        } else if (item.itemId == R.id.actionSearch) {
            return true
        }
        if (item.itemId == R.id.actionShare) {
            return true
        }
        if (item.itemId == R.id.actionTransferToCategory) {
            return true
        }
        if (item.itemId == R.id.actionDelete) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        loadWishLists()
    }

    private fun initializeView() {
        (activity as AppCompatActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_hamburger_24)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout = (activity as AppCompatActivity).findViewById(R.id.drawer_layout)
        val navView: NavigationView = (activity as AppCompatActivity).findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener { menuItem ->
            when {
                menuItem.toString() == resources.getString(R.string.buffer) -> {
                }
                menuItem.toString() == resources.getString(R.string.settings) -> {
                    navigator.showSettings(appContext)
                }
                menuItem.toString() == resources.getString(R.string.faq) -> {
                }
                menuItem.toString() == resources.getString(R.string.invite_friends) -> {
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val fabAdd: FloatingActionButton = (activity as WishListsActivity).getFabAdd()
        fabAdd.visibility = View.VISIBLE
        fabAdd.setOnClickListener {
            navigator.showDeals(appContext)
        }

        wishRecyclerView.layoutManager = LinearLayoutManager(context)
        wishRecyclerView.adapter = wishListsAdapter
        wishListsAdapter.shortClickListener = {
            if (it is WishCategoryView) {
                wishListsAdapter.expandOrCollapseCategory(it)
            } else if (it is WishProductView) {
                navigator.showChooseShop(appContext, it.id, it.name)
            }
        }
        wishListsAdapter.longClickListener = {
            if (it is WishCategoryView) {
                wishListsAdapter.selectCategory(it)
            } else if (it is WishProductView) {
                wishListsAdapter.selectProduct(it)
            }
            if (wishListsAdapter.areThereSelectedItems()) {
                changeOptionsMenuView(true)
            } else {
                changeOptionsMenuView(false)
            }
        }
    }

    private fun changeOptionsMenuView(itemsSelected: Boolean) {
        optionsMenu.findItem(R.id.actionShare).isVisible = itemsSelected
        optionsMenu.findItem(R.id.actionTransferToCategory).isVisible = itemsSelected
        optionsMenu.findItem(R.id.actionDelete).isVisible = itemsSelected
        optionsMenu.findItem(R.id.actionSearch).isVisible = !itemsSelected
    }

    private fun loadWishLists() {
        wishRecyclerView.visible()
        emptyView.invisible()
        showProgress()
        wishListsViewModel.loadWishLists(preferencesStorage.getUserId())
    }

    private fun renderWishLists(wishes: List<WishView>?) {
        if (wishes != null) {
            wishListsAdapter.collection = wishes as ArrayList<WishView>
        } else {
            handleFailure(WishListsFailure.ListNotAvailable())
        }
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is ServerError -> renderFailure(R.string.failure_server_error)
            is WishListsFailure.ListNotAvailable -> renderFailure(R.string.failure_wish_list_unavailable)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        wishRecyclerView.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.failure_action_refresh, ::loadWishLists)
    }
}