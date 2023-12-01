package com.isticpla.itp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isticpla.itp.feed.*
import com.isticpla.itp.home.*
import com.isticpla.itp.offerdetails.*
import com.isticpla.itp.offers.*
import com.isticpla.itp.profile.*
import com.isticpla.itp.signup.*
import com.isticpla.itp.splash.*
import com.isticpla.itp.views.*

@SuppressLint("NewApi")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun AppNavigate() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        /*composable("splash") { Splash(navController) }*/
        composable(Screen.StartSelectCulture.route) { StartSelectCulture(navController) }
        composable(Screen.AppIntro.route) { AppIntro(navController) }
        composable(Screen.SignUp.route) { SignUp(navController) }
        composable(Screen.VerifyPhoneNumber.route) { VerifyPhoneNumber(navController) }
        composable(Screen.CreateUserAccount.route) { CreateUserAccount(navController) }
        composable(Screen.AddYourBusiness.route) { AddYourBusiness(navController) }
        composable(Screen.ChooseBusinessSalesAreas.route) { ChooseBusinessSalesAreas(navController) }

        composable(Screen.Home.route) { Home(navController) }
        composable(Screen.Notifications.route) { Notifications(navController) }
        composable(Screen.Jobs.route) { Jobs(navController) }
        composable(Screen.Messages.route) { Messages(navController) }

        composable(Screen.CampaignsDashboards.route) { CampaignsDashboards(navController) }

        composable(Screen.FeedDashboard.route) { FeedDashboard(navController) }
        composable(Screen.FeedProductDetail.route) { FeedProductDetail(navController) }

        composable(Screen.MyPanel.route) { MyPanel(navController) }
        composable(Screen.MyPanelActiveOffers.route) { MyPanelActiveOffers(navController) }
        composable(Screen.MyPanelCollections.route) { MyPanelCollections(navController) }
        composable(Screen.MyPanelCollectionSub.route) { MyPanelCollectionSub(navController) }

        composable(Screen.NewsListDashboard.route) { NewsListDashboard(navController) }
        composable(Screen.NewsListDetail.route) { NewsListDetail(navController) }

        composable(Screen.StockSalesDashboard.route) { StockSalesDashboard(navController) }

        composable(Screen.CreateOfferDashboard.route) { CreateOfferDashboard(navController) }
        composable(Screen.CreateOfferPage1.route) { CreateOfferPage1(navController) }
        composable(Screen.CreateOfferVisualDetails.route) { CreateOfferVisualDetails(navController) }
        composable(Screen.CreateOfferProductDetails.route) { CreateOfferProductDetails(navController) }
        composable(Screen.CreateOfferProductParts.route) { CreateOfferProductParts(navController) }
        composable(Screen.CreateOfferRequestDetails.route) { CreateOfferRequestDetails(navController) }
        composable(Screen.CreateOfferPreview.route) { CreateOfferPreview(navController) }
        composable(Screen.CreateOfferPublish.route) { CreateOfferPublish(navController) }
        composable(Screen.CreateOfferAsService.route) { CreateOfferAsService(navController) }


        composable(Screen.OfferDetailDashboard.route) { OfferDetailDashboard(navController) }

        composable(Screen.ProfileDashboard.route) { ProfileDashboard(navController) }
        composable(Screen.Edit.route) { Edit(navController) }
        composable(Screen.ProfileSettings.route) { ProfileSettings(navController) }
        composable(Screen.MyStores.route) { MyStores(navController) }
        composable(Screen.MyStoreAddEdit.route) { MyStoreAddEdit(navController) }
        composable(Screen.ContractedSuppliers.route) { ContractedSuppliers(navController) }
        composable(Screen.CollectionsAndTags.route) { CollectionsAndTags(navController) }
        composable(Screen.HelpAndSupport.route) { HelpAndSupport(navController) }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int? = null) {
    object StartSelectCulture : Screen(route = "startselectculture")
    object AppIntro : Screen(route = "appintro")
    object SignUp : Screen(route = "signup")
    object VerifyPhoneNumber : Screen(route = "verifyphonenumber")
    object CreateUserAccount : Screen(route = "createuseraccount")
    object AddYourBusiness : Screen(route = "addyourbusiness")
    object ChooseBusinessSalesAreas : Screen(route = "choosebusinesssalesareas")
    object Home : Screen(route = "home")
    object Notifications : Screen(route = "hnotifications")
    object Jobs : Screen(route = "hjobs")
    object Messages : Screen(route = "hmessages")
    object CampaignsDashboards : Screen(route = "campaigns")
    object FeedDashboard : Screen(route = "feed")
    object FeedProductDetail : Screen(route = "feed/productdetail")
    object MyPanel : Screen(route = "mypanel")
    object MyPanelActiveOffers : Screen(route = "mypanel/activeoffers")
    object MyPanelCollections : Screen(route = "mypanel/collections")
    object MyPanelCollectionSub : Screen(route = "mypanel/collections/sub")
    object NewsListDashboard : Screen(route = "newlist")
    object NewsListDetail : Screen(route = "newslist/detail")
    object StockSalesDashboard : Screen(route = "stocksales")
    object CreateOfferDashboard : Screen(route = "offer/dashboard")
    object CreateOfferPage1 : Screen(route = "offer/create")
    object CreateOfferVisualDetails : Screen(route = "offer/create/visualdetails")
    object CreateOfferProductDetails : Screen(route = "offer/create/productdetails")
    object CreateOfferProductParts : Screen(route = "offer/create/productdetails/parts")
    object CreateOfferRequestDetails : Screen(route = "offer/create/requestdetails")
    object CreateOfferPreview : Screen(route = "offer/create/preview")
    object CreateOfferPublish : Screen(route = "offer/create/publish")
    object CreateOfferAsService : Screen(route = "offer/create/asservice")
    object OfferDetailDashboard : Screen(route = "offer/detail/dashboard")
    object ProfileDashboard : Screen(route = "profile/dashboard")
    object Edit : Screen(route = "profile/edit")
    object ProfileSettings : Screen(route = "profile/settings")
    object MyStores : Screen(route = "profile/mystores")
    object MyStoreAddEdit : Screen(route = "profile/mystores/edit")
    object ContractedSuppliers : Screen(route = "profile/contractedSuppliers")
    object CollectionsAndTags : Screen(route = "profile/collectionsandtags")
    object HelpAndSupport : Screen(route = "profile/helpandsupport")


}