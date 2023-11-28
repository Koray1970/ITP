package com.isticpla.itp

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
    NavHost(navController = navController, startDestination = "offer/create/productdetails/parts") {
        composable("splash") { Splash(navController) }
        composable("startselectculture") { StartSelectCulture(navController) }
        composable("appintro") { AppIntro(navController) }
        composable("signup") { SignUp(navController) }
        composable("verifyphonenumber") { VerifyPhoneNumber(navController) }
        composable("createuseraccount") { CreateUserAccount(navController) }
        composable("addyourbusiness") { AddYourBusiness(navController) }
        composable("choosebusinesssalesareas") { ChooseBusinessSalesAreas(navController) }

        composable("home") { Home(navController) }
        composable("home/notifications") { Notifications(navController) }
        composable("home/jobs") { Jobs(navController) }
        composable("home/messages") { Messages(navController) }

        composable("home/section/campaigns") { HomeSectionCampaigns(navController) }

        composable("feed") { FeedDashboard(navController) }
        composable("feed/productdetail") { FeedProductDetail(navController) }


        composable("mypanel") { MyPanel(navController) }
        composable("mypanel/activeoffers") { MyPanelActiveOffers(navController) }
        composable("mypanel/collections") { MyPanelCollections(navController) }
        composable("mypanel/collections/sub") { MyPanelCollectionSub(navController) }

        composable("newlist") { NewsListDashboard(navController) }
        composable("newslist/detail") { NewsListDetail(navController) }

        composable("stocksales") { StockSalesDashboard(navController) }

        composable("offer/dashboard") { CreateOfferDashboard(navController) }
        composable("offer/create") { CreateOfferPage1(navController) }
        composable("offer/create/visualdetails") { CreateOfferVisualDetails(navController) }
        composable("offer/create/productdetails") { CreateOfferProductDetails(navController) }
        composable("offer/create/productdetails/parts") { CreateOfferProductParts(navController) }
        composable("offer/create/requestdetails") { CreateOfferRequestDetails(navController) }
        composable("offer/create/preview") { CreateOfferPreview(navController) }
        composable("offer/create/publish") { CreateOfferPublish(navController) }


        composable("offer/detail/dashboard") { OfferDetailDashboard(navController) }

        composable("profile/dashboard") { ProfileDashboard(navController) }
        composable("profile/edit") { com.isticpla.itp.profile.Edit(navController) }
        composable("profile/settings") { com.isticpla.itp.profile.ProfileSettings(navController) }
        composable("profile/mystores") { com.isticpla.itp.profile.MyStores(navController) }
        composable("profile/mystores/edit") { com.isticpla.itp.profile.MyStoreAddEdit(navController) }
        composable("profile/contractedSuppliers") { com.isticpla.itp.profile.ContractedSuppliers(navController) }
        composable("profile/collectionsandtags") { com.isticpla.itp.profile.CollectionsAndTags(navController) }
        composable("profile/helpandsupport") { com.isticpla.itp.profile.HelpAndSupport(navController) }
    }
}