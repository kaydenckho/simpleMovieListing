package com.example.simplecryptolisting.util

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.simplecryptolisting.MyApplication
import com.example.simplecryptolisting.R
import com.google.gson.Gson
import java.lang.reflect.Type

fun InternalDeepLink(view: View) = InternalDeepLink(view.context, view.findNavController())
fun InternalDeepLink(fragment: Fragment) = InternalDeepLink(MyApplication.getContext(), fragment.findNavController())

class InternalDeepLink(val context: Context, val navController: NavController) {

    companion object {
        const val PAGE_BUNDLE_MODEL = "{pageBundleModel}"
        const val PAGE_BUNDLE_MODEL_STRING = "pageBundleModel"
        fun <T>convertStringToModel(json: String, typeOfT: Type): T? {
            return try{
                Gson().fromJson(json, typeOfT) as T
            }catch (e: Exception){
                null
            }
        }

        fun convertModelToJsonString(model: Any?, typeOfSrc: Type): String{
            var convertString = ""
             runCatching {
                Gson().toJsonTree(model, typeOfSrc).toString()
            }.onSuccess {
                 convertString = it
            }.onFailure {
                 convertString = ""
            }
            return convertString
        }

    }

    fun goSymbolFragment(symbol: String){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_symbol)
        val updatedUrl = deepLinkUrl
            .replace("{symbol}", symbol)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    private fun genNavOptions(slideInType: String, popUpId: Int?, inclusive: Boolean): NavOptions {
        val navOptions = NavOptions.Builder()
        when(slideInType.toLowerCase()){
            "top" -> {
                navOptions.setExitAnim(R.anim.fade_out)
                navOptions.setPopEnterAnim(R.anim.fade_in)
                navOptions.setEnterAnim(R.anim.slide_in_top)
                navOptions.setPopExitAnim(R.anim.slide_out_top)
            }
            "bottom" -> {
                navOptions.setExitAnim(R.anim.fade_out)
                navOptions.setPopEnterAnim(R.anim.fade_in)
                navOptions.setEnterAnim(R.anim.slide_in_bottom)
                navOptions.setPopExitAnim(R.anim.slide_out_bottom)
            }
            "left" -> {
                navOptions.setExitAnim(R.anim.fade_out)
                navOptions.setPopEnterAnim(R.anim.fade_in)
                navOptions.setEnterAnim(R.anim.slide_in_left)
                navOptions.setPopExitAnim(R.anim.slide_out_left)
            }
            "right" -> {
                navOptions.setExitAnim(R.anim.fade_out)
                navOptions.setPopEnterAnim(R.anim.fade_in)
                navOptions.setEnterAnim(R.anim.slide_in_right)
                navOptions.setPopExitAnim(R.anim.slide_out_right)
            }
            else -> {

            }
        }

        if (popUpId != null){
            navOptions.setPopUpTo(popUpId, inclusive)
        }

        return navOptions.build()
    }

    private fun navDeepLinkCall(navDeepLinkUrl: Uri, navOptions: NavOptions) {
        try {
            navController.navigate(navDeepLinkUrl, navOptions)
        } catch (e: Exception) {
        }
    }

}