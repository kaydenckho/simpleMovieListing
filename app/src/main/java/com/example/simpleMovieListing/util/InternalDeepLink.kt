package com.example.simpleMovieListing.util

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.simpleMovieListing.MyApplication
import com.example.simpleMovieListing.R
import com.example.simpleMovieListing.model.Movie
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

    fun goDetailFragment(movie: Movie){
        var deepLinkUrl = context.getString(R.string.internal_deeplink_detail)
        val modelString = convertModelToJsonString(movie, Movie::class.java)
        deepLinkUrl = deepLinkUrl.replace(PAGE_BUNDLE_MODEL, modelString)
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("right", null, false))
    }

    private fun genNavOptions(slideInType: String, popUpId: Int?, inclusive: Boolean): NavOptions {
        val navOptions = NavOptions.Builder()
        when(slideInType.toLowerCase()){
            "top" -> {
                navOptions.setPopEnterAnim(R.anim.slide_in_top)
                navOptions.setEnterAnim(R.anim.slide_in_top)
                navOptions.setExitAnim(R.anim.slide_out_bottom)
                navOptions.setPopExitAnim(R.anim.slide_out_bottom)
            }
            "bottom" -> {
                navOptions.setPopEnterAnim(R.anim.slide_in_bottom)
                navOptions.setEnterAnim(R.anim.slide_in_bottom)
                navOptions.setExitAnim(R.anim.slide_out_top)
                navOptions.setPopExitAnim(R.anim.slide_out_top)
            }
            "left" -> {
                navOptions.setPopEnterAnim(R.anim.slide_in_left)
                navOptions.setEnterAnim(R.anim.slide_in_left)
                navOptions.setExitAnim(R.anim.slide_out_right)
                navOptions.setPopExitAnim(R.anim.slide_out_right)
            }
            "right" -> {
                navOptions.setPopEnterAnim(R.anim.slide_in_right)
                navOptions.setEnterAnim(R.anim.slide_in_right)
                navOptions.setExitAnim(R.anim.slide_out_left)
                navOptions.setPopExitAnim(R.anim.slide_out_left)
            }
            else -> {
                navOptions.setPopEnterAnim(R.anim.fade_in)
                navOptions.setEnterAnim(R.anim.fade_in)
                navOptions.setExitAnim(R.anim.fade_out)
                navOptions.setPopExitAnim(R.anim.fade_out)
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