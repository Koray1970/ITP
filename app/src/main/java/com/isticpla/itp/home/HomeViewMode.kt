package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isticpla.itp.data.countryListDB
import com.isticpla.itp.dummydata.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewMode @Inject constructor() : ViewModel() {
    val carouselList = flowOf<List<Int>>(listofCarousel)


    val areacodeList = flowOf<List<Pair<String, String>>>(listofAraeCodes)
    val countryList = flowOf<List<Pair<String, String>>>(countryListDB)

    //@RequiresApi(Build.VERSION_CODES.R)
    val shopList = flowOf<List<Pair<Int, String>>>(listofShops)
    var sectorList = flowOf<MutableList<BusinessTypeItem>>(listofBusiness.toMutableList())
    val designsList = flowOf<List<HomeDesignItem>>(listofDesigns)
    val campaignList = flowOf<List<HomeCampaignItem>>(listofHomeCampaigns)
    val stokSaleList = flowOf<List<HomeDesignItem>>(listofStokSale)
    val sectorNewsList = flowOf<List<SectorNewsItem>>(listofHomeSectorNews)
    val offerDrafts = flowOf<List<OfferDraftListItem>>(listOfOfferDraft)
    val orderStages = flowOf<List<OrderStages>>(listOfOrderStages)
    val productDRPItems = flowOf<List<ProductFeatureItem>>(listofProductFeature)

    val requestQuantity = flowOf<List<Pair<Int, Int>>>(listOfQuantity)
    val requestPaymentType = flowOf<List<Pair<Int, String>>>(listOfPaymentType)
    val requestDeliveryTypes = flowOf<List<Pair<Int, String>>>(listOfDeliveryType)

    val feedDashboardItems = flowOf<List<FeedDashboardItems>>(listofFeedDashboard)

    val offerDetailsTabs = flowOf<List<OfferDetailTabItem>>(listofOfferDetailTabs)
    val offerDetailsTracklist = flowOf<List<OfferDetailTrackingItem>>(listofOfferDetailTrackings)
    val offerDetailsChat = flowOf<List<OfferChatItem>>(listofChatMessages)

    val profileMenu = flowOf<List<ProfileMenuItem>>(listofProfileMenu)


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
        return listofBusiness.first { i -> i.id == id }.isSelected!!
    }

    fun UpdateSectorListSelection(id: Int) = viewModelScope.launch {
        sectorList.collect {
            it.forEach { b ->
                b.isSelected = b.id != id
            }
        }
    }
}