package com.example.simplecryptolisting.util

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hket.android.up.EpcApp
import com.hket.android.up.R
import com.hket.android.up.database.menu.model.SubMenuDBM
import com.hket.android.up.database.menu.model.SubSubMenuDBM
import com.hket.android.up.ui.channelSlider.ChannelSliderFragment
import com.hket.android.up.ui.channelSlider.ChannelSliderPageModel
import com.hket.android.up.ui.community.postCreate.`object`.PostCreateImageDetail
import com.hket.android.up.ui.community.postCreate.`object`.PostCreateTagDetail
import com.hket.android.up.ui.community.postDetail.`object`.PostDetailDataModel
import com.hket.android.up.ui.community.postListingSlider.model.CmuTagItem
import java.lang.reflect.Type
import javax.annotation.Nullable

fun InternalDeepLink(view: View) = InternalDeepLink(view.context, view.findNavController())
fun InternalDeepLink(fragment: NavHostFragment) = InternalDeepLink(EpcApp.getContext(), fragment.navController)
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


    fun goJetSoMainFragment(){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_jetSo_main)
        navController.graph.startDestination = R.id.jetSoMainFragment
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("", navController.currentDestination?.id ?: R.id.channelSliderFragment, true))
    }

    fun goTVMainFragment(){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_tv_main)
        navController.graph.startDestination = R.id.TVMainFragment
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("", navController.currentDestination?.id ?: R.id.channelSliderFragment, true))
    }

    fun goChannelSliderFragment(channelSliderPM: ChannelSliderPageModel){
        var deepLinkUrl = context.getString(R.string.internal_deeplink_main_channel_slider)
        val modelString = convertModelToJsonString(channelSliderPM, ChannelSliderPageModel::class.java)
        deepLinkUrl = deepLinkUrl.replace(PAGE_BUNDLE_MODEL, modelString)
        navController.graph.startDestination = R.id.channelSliderFragment
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("", navController.currentDestination?.id ?: R.id.channelSliderFragment, true))
    }

    fun goMiniProgramFragment(){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_miniProgram)
        navController.graph.startDestination = R.id.miniProgramFragment
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("", navController.currentDestination?.id ?: R.id.channelSliderFragment, true))
    }

    fun goMemberActivitiesFragment(subMenuDBM: SubMenuDBM?){
        var deepLinkUrl = context.getString(R.string.internal_deeplink_member_activities_listing)
        subMenuDBM?.let {
            val modelString = convertModelToJsonString(it, SubMenuDBM::class.java)
            deepLinkUrl = deepLinkUrl.replace(PAGE_BUNDLE_MODEL, modelString)
        }
        navController.graph.startDestination = R.id.memberActivitiesFragment
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("", navController.currentDestination?.id ?: R.id.channelSliderFragment, true))
    }

    fun goCmuMainLandingFragment(){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_main_landing)
        navController.graph.startDestination = R.id.cmuMainLandingFragment
        navDeepLinkCall(deepLinkUrl.toUri(), genNavOptions("", navController.currentDestination?.id ?: R.id.channelSliderFragment, true))
    }

    fun goTVSectionFragment(tvSectionLink: String){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_tv_section)
        val updatedUrl = deepLinkUrl.replace("{loadMoreLandingPageApi}", tvSectionLink)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("bottom", null, false))
    }

    fun goTVSearchResultFragment(
        title: String,
        searchType: String,
        searchHint: String,
        searchLoadingApi: String,
        videoSeries: String,
        shouldShowHeader: Boolean
    ) {
        val deepLinkUrl = context.getString(R.string.internal_deeplink_tv_search_result)
        val updatedUrl = deepLinkUrl
            .replace("{title}", title)
            .replace("{searchType}", searchType)
            .replace("{searchHint}", searchHint)
            .replace("{searchLoadingApi}", searchLoadingApi)
            .replace("{videoSeries}", videoSeries)
            .replace("{shouldShowHeader}", shouldShowHeader.toString())

        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("bottom", null, false))
    }

    fun goSearchMainFragment(isTVFlow: Boolean, SearchBarTag: String){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_main_search)
        val updatedUrl = deepLinkUrl
            .replace("{isTVFlow}", isTVFlow.toString())
            .replace("{SearchBarTag}", SearchBarTag)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("", null, false))
    }

    fun goSearchTabFragment(
        title: String,
        searchType: String,
        searchHint: String,
        searchLoadingApi: String,
        videoSeries: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_main_search_tab)
        val updatedUrl = deepLinkUrl
            .replace("{title}", title)
            .replace("{searchType}", searchType)
            .replace("{searchHint}", searchHint)
            .replace("{searchLoadingApi}", searchLoadingApi)
            .replace("{videoSeries}", videoSeries)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("bottom", null, false))
    }
    
    fun goCmuPostDetailFragment(
        cmuPostId: String, 
        tagId: String, 
        scrollToComment: Boolean
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_detail)
        val updatedUrl = deepLinkUrl
            .replace("{cmuPostId}", cmuPostId)
            .replace("{tagId}", tagId)
            .replace("{scrollToComment}", scrollToComment.toString())
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuPostDetailCommentFragment(
        postDetailDataModel: PostDetailDataModel?,
        cmuPostId: String,
        cmuReactionId: String
    ){

        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_detail_comment)
        val updatedUrl = deepLinkUrl
            .replace("{postDetailDataModel}", Gson().toJsonTree(postDetailDataModel, PostDetailDataModel::class.java).toString())
            .replace("{cmuPostId}", cmuPostId)
            .replace("{cmuReactionId}", cmuReactionId)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("", null, false))
    }
    
    fun goCmuThemeLandingFragment(
        tagId: String,
        tagName: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_theme_landing)
        val updatedUrl = deepLinkUrl
            .replace("{tagId}", tagId)
            .replace("{tagName}", tagName)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuPostListingSliderFragment(
        tagItem: CmuTagItem,
        targetTagId: String,
        pageType: String,
        displayTabBar: Boolean,
        searchKeyword: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_listing_slider)
        val updatedUrl = deepLinkUrl
            .replace("{tagItem}", Gson().toJsonTree(tagItem, CmuTagItem::class.java).toString())
            .replace("{targetTagId}", targetTagId)
            .replace("{pageType}", pageType)
            .replace("{displayTabBar}", displayTabBar.toString())
            .replace("{searchKeyword}", searchKeyword)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }
    
    fun goCmuPostCreateFragment(
        postId: String,
        mainTagItem: PostCreateTagDetail?
        ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_create)
        val updatedUrl = deepLinkUrl
            .replace("{postId}", postId)
            .replace("{mainTagItem}", Gson().toJsonTree(mainTagItem, PostCreateTagDetail::class.java).toString())
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("bottom", null, false))
    }

    fun goCmuPostCreateChooseTagFragment(
        tagType: Int,
        selectedList: MutableList<PostCreateTagDetail>?,
        mainTagId: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_create_choose_tag)
        val selectedListType = object : TypeToken<List<PostCreateTagDetail>>() {}.type
        val updatedUrl = deepLinkUrl
            .replace("{tagType}", tagType.toString())
            .replace("{selectedList}", Gson().toJsonTree(selectedList, selectedListType).toString())
            .replace("{mainTagId}", mainTagId)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("", null, false))
    }

    fun goCmuPostCreateImageGalleryFragment(
        selectedPosition: Int,
        imageDetailList: List<PostCreateImageDetail>?,
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_create_image_gallery)
        val selectedListType = object : TypeToken<List<PostCreateImageDetail>>() {}.type
        val updatedUrl = deepLinkUrl
            .replace("{selectedPosition}", selectedPosition.toString())
            .replace("{imageDetailList}", Gson().toJsonTree(imageDetailList, selectedListType).toString())
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("", null, false))
    }

    fun goCmuPostListingFragment(
        tagItem: CmuTagItem,
        sliderPosition: Int,
        isShowHeader: Boolean,
        pageType: String,
        searchKeyword: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_listing)
        val updatedUrl = deepLinkUrl
            .replace("{tagItem}", Gson().toJsonTree(tagItem, CmuTagItem::class.java).toString())
            .replace("{sliderPosition}", sliderPosition.toString())
            .replace("{isShowHeader}", isShowHeader.toString())
            .replace("{pageType}", pageType)
            .replace("{searchKeyword}", searchKeyword)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuSearchCmuFragment(
        enableSearchListing: Boolean,
        tagId: String,
        tagName: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_search)
        val updatedUrl = deepLinkUrl
            .replace("{enableSearchListing}", enableSearchListing.toString())
            .replace("{tagId}", tagId)
            .replace("{tagName}", tagName)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuProfileSliderFragment(
        followingListingType: String,
        sliderPosition: Int,
        searchUserId: String,
        searchUserName: String,
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_profile_slider)
        val updatedUrl = deepLinkUrl
            .replace("{followingListingType}", followingListingType)
            .replace("{sliderPosition}", sliderPosition.toString())
            .replace("{searchUserId}", searchUserId)
            .replace("{searchUserName}", searchUserName)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuProfileListingFragment(
        listingType: String,
        listingTypeName: String,
        listingShowHeader: Boolean,
        searchKeyword: String,
        searchUserId: String,
        postId: String,
        postTitle: String,
        postMainTopic: String,
        postSubTopic: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_profile_listing)
        val updatedUrl = deepLinkUrl
            .replace("{listingType}", listingType)
            .replace("{listingTypeName}", listingTypeName)
            .replace("{listingShowHeader}", listingShowHeader.toString())
            .replace("{searchKeyword}", searchKeyword)
            .replace("{searchUserId}", searchUserId)
            .replace("{postId}", postId)
            .replace("{postTitle}", postTitle)
            .replace("{postMainTopic}", postMainTopic)
            .replace("{postSubTopic}", postSubTopic)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("", null, false))
    }

    fun goCmuSearchResultFragment(
        keyword: String,
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_search_result)
        val updatedUrl = deepLinkUrl
            .replace("{keyword}", keyword)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuGalleryFragment(
        currentPosition: Int,
        postDetailData: PostDetailDataModel,
        enableGotoPostDetail: Boolean,
        postId: String
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_gallery)
        val updatedUrl = deepLinkUrl
            .replace("{currentPosition}", currentPosition.toString())
            .replace("{postDetailData}", Gson().toJsonTree(postDetailData, PostDetailDataModel::class.java).toString())
            .replace("{enableGotoPostDetail}", enableGotoPostDetail.toString())
            .replace("{postId}", postId)
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuPushArticleFragment(
        searchTagString: String,
        searchTagTitle: String,
        isMainLandingIn: Boolean
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_push_article)
        val updatedUrl = deepLinkUrl
            .replace("{searchTagString}", searchTagString)
            .replace("{searchTagTitle}", searchTagTitle)
            .replace("{isMainLandingIn}", isMainLandingIn.toString())
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("", null, false))
    }

    fun goCmuProfileLandingFragment(
        searchUserId: String,
        isDisplayNonLoginLayout: Boolean
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_profile_landing)
        val updatedUrl = deepLinkUrl
            .replace("{searchUserId}", searchUserId)
            .replace("{isDisplayNonLoginLayout}", isDisplayNonLoginLayout.toString())
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("right", null, false))
    }

    fun goCmuPostDetailReactionBottomSheetFragment(
        postId: String,
        postTitle: String,
        postMainTopic: String,
        postSubTopic: String,
        likeCount: Int
    ){
        val deepLinkUrl = context.getString(R.string.internal_deeplink_cmu_post_detail_reaction_bot_sheet)
        val updatedUrl = deepLinkUrl
            .replace("{postId}", postId)
            .replace("{postTitle}", postTitle)
            .replace("{postMainTopic}", postMainTopic)
            .replace("{postSubTopic}", postSubTopic)
            .replace("{likeCount}", likeCount.toString())
        navDeepLinkCall(updatedUrl.toUri(), genNavOptions("bottom", null, false))
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
            LoggerUtil.devInfoLogger("ToPageUtil", "navDeepLinkCall $navDeepLinkUrl")
            navController.navigate(navDeepLinkUrl, navOptions)
        } catch (e: Exception) {
            LoggerUtil.devErrLogger("ToPageUtil", e.toString())
        }
    }


}