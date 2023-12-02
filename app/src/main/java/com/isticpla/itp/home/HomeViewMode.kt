package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isticpla.itp.data.GetCountryList
import com.isticpla.itp.data.countryListDB
import com.isticpla.itp.dummydata.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewMode @Inject constructor() : ViewModel() {
    val appCultures = flowOf<List<AppCultureDataModel>>(listOfAppCulture)

    val carouselList = flowOf<List<Int>>(listofCarousel)

    val employeePositions = flowOf<List<Pair<String, String>>>(listofEmployeePosition)
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getEmployeePositionResul(id: String) =
        employeePositions.mapLatest { i -> i.filter { it.first == id } }

    val areacodeList = flowOf<List<Pair<String, String>>>(listofAraeCodes)
    val countryList = flowOf<List<Pair<String, String>>>(GetCountryList())

    @OptIn(ExperimentalCoroutinesApi::class)
    fun countryResult(code: String) =
        countryList.mapLatest { i -> i.filter { it.first == code } }


    //@RequiresApi(Build.VERSION_CODES.R)
    val shopList = flowOf<List<ShopItem>>(listofShops)

    fun shopListAsPairs() = flow<List<Pair<Int, String>>> {

        val listofShop = mutableListOf<Pair<Int, String>>()
        listofShops.forEach {
            listofShop.add(Pair(it.id, it.name))
        }
        emit(listofShop.toList())
    }

    var sectorList = flowOf<MutableList<BusinessTypeItem>>(listofBusiness.filter { a -> !a.except }
        .toMutableList())
    var sectorOthers = flowOf<BusinessTypeItem>(listofBusiness.first { a -> a.except })

    val designsList = flowOf<List<HomeDesignItem>>(listofDesigns)
    val campaignList = flowOf<List<HomeCampaignItem>>(listofHomeCampaigns)
    val stokSaleList = flowOf<List<HomeDesignItem>>(listofStokSale)
    val sectorNewsList = flowOf<List<SectorNewsItem>>(listofHomeSectorNews)
    val offerDrafts = flowOf<List<OfferDraftListItem>>(listOfOfferDraft)
    val orderStages = flowOf<List<OrderStages>>(listOfOrderStages)
    val productDRPItems =
        flowOf<List<ProductFeatureItem>>(listofProductFeature.sortedBy { a -> a.label })

    val requestQuantity = flowOf<List<Pair<Int, Int>>>(listOfQuantity)
    val requestPaymentType = flowOf<List<Pair<Int, String>>>(listOfPaymentType)
    val requestDeliveryTypes = flowOf<List<Pair<Int, String>>>(listOfDeliveryType)

    val feedDashboardItems = flowOf<List<FeedDashboardItems>>(listofFeedDashboard)

    val offerDetailsTabs = flowOf<List<OfferDetailTabItem>>(listofOfferDetailTabs)
    val offerDetailsTracklist = flowOf<List<OfferDetailTrackingItem>>(listofOfferDetailTrackings)
    val offerDetailsChat = flowOf<List<OfferChatItem>>(listofChatMessages)

    val profileMenu = flowOf<List<ProfileMenuItem>>(listofProfileMenu)

    val panelActiveOffers = flowOf<List<PanelActiveOfferItem>>(listofPanelActiveOffer)
    val panelCollections = flowOf<List<PanelCollectionItem>>(listofPanelCollection)
    val panelCollectionSub = flowOf<List<PanelCollectionItem>>(listofPanelCollectionSub)
    val panelDrafts = flowOf<List<PanelOfferDraftItem>>(listofPanelOfferDraft)
    val panelCompletedOffers = flowOf<List<PanelOfferCompletedItem>>(listofPanelOfferCompleted)

    val appIntroList= flowOf<List<AppIntroDataModel>>(AppIntroData)
    fun mystores(isfiltered: Boolean) = flow<List<MyStoreItem>> {
        if (isfiltered)
            this.emit(listofMyStore.filter { a -> a.isactive })
        else
            this.emit(listofMyStore)
    }.flowOn(Dispatchers.IO)

    fun contractedSuppliers(isfiltered: Boolean) = flow<List<ContractedSupplierItem>> {
        if (isfiltered)
            this.emit(listofContractedSupplier.filter { a -> a.isactive })
        else
            this.emit(listofContractedSupplier)
    }.flowOn(Dispatchers.IO)

    fun updateSectorButtonIsEnabled(id: Int): Boolean {
        return listofBusiness.first { i -> i.id == id }.isSelected.value
    }

    fun UpdateSectorListSelection(id: Int) = viewModelScope.launch {
        sectorList.collect {
            it.forEach { b ->
                b.isSelected.value = b.id != id
            }
        }
    }
}