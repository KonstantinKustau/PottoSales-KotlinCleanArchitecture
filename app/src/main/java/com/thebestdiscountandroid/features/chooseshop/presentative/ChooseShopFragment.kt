package com.thebestdiscountandroid.features.chooseshop.presentative

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
import com.thebestdiscountandroid.features.chooseshop.domain.ProductLimitEntity
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ChooseShopAdapter
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ShopHeaderView
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ShopItemView
import com.thebestdiscountandroid.features.chooseshop.presentative.recyclerview.ShopView
import kotlinx.android.synthetic.main.choose_shop_fragment.*
import kotlinx.android.synthetic.main.empty_layout.emptyView
import javax.inject.Inject

class ChooseShopFragment : BaseFragment() {

    companion object {
        const val LIMIT_HEADER: String = "limit:"
        const val NO_LIMIT_HEADER: String = "No limit"

        private const val PARAM_PRODUCT_ID = "param_product_id"
        private const val PARAM_PRODUCT_NAME = "param_product_name"

        fun forProduct(productId: Int, productName: String?): ChooseShopFragment {
            val chooseShopFragment = ChooseShopFragment()
            val arguments = Bundle()
            arguments.putInt(PARAM_PRODUCT_ID, productId)
            arguments.putString(PARAM_PRODUCT_NAME, productName)
            chooseShopFragment.arguments = arguments
            return chooseShopFragment
        }
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var chooseShopAdapter: ChooseShopAdapter

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    private lateinit var chooseShopViewModel: ChooseShopViewModel

    private lateinit var optionsMenu: Menu

    override fun layoutId() = R.layout.choose_shop_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)

        chooseShopViewModel = viewModel(viewModelFactory) {
            observe(shops, ::renderShops)
            observe(limit, ::renderLimit)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_choose_shop, menu)
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
        if (item.itemId == R.id.actionLimit) {
            setLimitDialog()
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
        loadShopsAndLimits()
    }

    private fun initializeView() {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        val productName: String? = arguments?.getString(PARAM_PRODUCT_NAME)
        if (productName != null) {
            (activity as AppCompatActivity).supportActionBar?.title = productName
        }
        shopsRecyclerView.layoutManager = LinearLayoutManager(context)
        shopsRecyclerView.adapter = chooseShopAdapter
        chooseShopAdapter.selectedColor = context?.getColor(R.color.colorSelectedTransparent) ?: 0
        chooseShopAdapter.shortClickListener = {
            if (it is ShopItemView) {
                chooseShopAdapter.selectShop(it)
                if (chooseShopAdapter.areThereSelectedItems()) {
                    changeOptionsMenuView(true)
                } else {
                    changeOptionsMenuView(false)
                }
            } else if (it is ShopHeaderView) {
                setLimitDialog()
            }
        }
        chooseShopAdapter.longClickListener = {}
    }

    private fun changeOptionsMenuView(itemsSelected: Boolean) {
        optionsMenu.findItem(R.id.actionDone).isVisible = itemsSelected
        optionsMenu.findItem(R.id.actionLimit).isVisible = !itemsSelected
        optionsMenu.findItem(R.id.actionSearch).isVisible = !itemsSelected
    }

    private fun setLimitDialog() {
    }

    private fun loadShopsAndLimits() {
        shopsRecyclerView.visible()
        emptyView.invisible()
        showProgress()

        val productId: Int? = arguments?.getInt(PARAM_PRODUCT_ID)
        if (productId != null) {
            chooseShopViewModel.loadShops(productId)
            chooseShopViewModel.loadLimits(preferencesStorage.getUserId(), productId)
        }
    }

    private fun renderShops(shopItems: List<ShopView>?) {
        if (shopItems != null) {
            chooseShopAdapter.collection = shopItems as ArrayList<ShopView>
        } else {
            handleFailure(ChooseShopFailure.ListNotAvailable())
        }
        hideProgress()
    }

    private fun renderLimit(limit: ProductLimitEntity?) {
        if (limit != null) {
            chooseShopAdapter.collection[0] = ShopHeaderView(limit.limit)
        } else {
            handleFailure(ChooseShopFailure.LoadingLimitError())
        }
        hideProgress()
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> renderFailure(R.string.failure_network_connection)
            is Failure.ServerError -> renderFailure(R.string.failure_server_error)
            is ChooseShopFailure.ListNotAvailable -> renderFailure(R.string.failure_wish_list_unavailable)
            is ChooseShopFailure.LoadingLimitError -> renderFailure(R.string.failure_loading_limit_error)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        shopsRecyclerView.invisible()
        emptyView.visible()
        hideProgress()
        notifyWithAction(message, R.string.failure_action_refresh, ::loadShopsAndLimits)
    }
}