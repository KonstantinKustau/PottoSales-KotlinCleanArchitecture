package com.thebestdiscountandroid.core.platform

import com.thebestdiscountandroid.AndroidApplication
import com.thebestdiscountandroid.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.io.InputStream
import javax.inject.Singleton

@Singleton
class MockInterceptor(val application: AndroidApplication) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val original = chain.request()
            val uri = original.url.toUri().toString()
            val responseString = when {
                uri.contains("getWishLists") -> loadJSON("mocks/wish/getWishLists.json")
                uri.contains("updateWishLists") -> loadJSON("mocks/wish/updateWishLists.json")
                uri.contains("logout") -> loadJSON("mocks/settings/logout.json")
                uri.contains("updateUserProperties") -> loadJSON("mocks/settings/updateUserProperties.json")
                uri.contains("signIn") -> loadJSON("mocks/login/signIn.json")
                uri.contains("signUp") -> loadJSON("mocks/login/signUp.json")
                uri.contains("getTopDeals") -> loadJSON("mocks/deals/getTopDeals.json")
                uri.contains("filterProductsByName") -> loadJSON("mocks/deals/filterProductsByName.json")
                uri.contains("getShopsByProduct") -> loadJSON("mocks/chooseshop/getShopsByProduct.json")
                uri.contains("setLimitOnUserProduct") -> loadJSON("mocks/chooseshop/setLimitOnUserProduct.json")
                uri.contains("getLimitsOnUser") -> loadJSON("mocks/chooseshop/getLimitsOnUser.json")
                else -> ""
            }

            val messageBody = ResponseBody.create(
                "application/json".toMediaTypeOrNull(), responseString.toByteArray()
            )
            return Response.Builder()
                .code(200)
                .request(original)
                .protocol(Protocol.HTTP_1_0)
                .message("test")
                .addHeader("content-type", "application/json")
                .body(messageBody)
                .build()
        } else {
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }

    private fun loadJSON(jsonPath: String): String {
        var json = ""
        json = try {
            val inputStream: InputStream = application.assets.open(jsonPath)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}