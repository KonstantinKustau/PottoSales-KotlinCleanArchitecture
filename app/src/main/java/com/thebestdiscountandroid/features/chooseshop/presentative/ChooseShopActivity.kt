package com.thebestdiscountandroid.features.chooseshop.presentative

import android.content.Context
import android.content.Intent
import com.thebestdiscountandroid.core.platform.BaseActivity

class ChooseShopActivity : BaseActivity() {

    companion object {
        private const val INTENT_EXTRA_PARAM_PRODUCT_ID =
            "com.thebestdiscountandroid.INTENT_EXTRA_PARAM_PRODUCT_ID"
        private const val INTENT_EXTRA_PARAM_PRODUCT_NAME =
            "com.thebestdiscountandroid.INTENT_EXTRA_PARAM_PRODUCT_NAME"

        fun callingIntent(context: Context, productId: Int, productName: String): Intent {
            val intent = Intent(context, ChooseShopActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_PRODUCT_ID, productId)
            intent.putExtra(INTENT_EXTRA_PARAM_PRODUCT_NAME, productName)
            return intent
        }
    }

    override fun fragment() =
        ChooseShopFragment.forProduct(
            intent.getIntExtra(INTENT_EXTRA_PARAM_PRODUCT_ID, -1),
            intent.getStringExtra(INTENT_EXTRA_PARAM_PRODUCT_NAME)
        )
}