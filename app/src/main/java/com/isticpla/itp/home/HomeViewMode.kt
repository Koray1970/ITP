package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isticpla.itp.dummydata.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewMode @Inject constructor() : ViewModel() {
    val carouselList = flowOf<List<Int>>(listofCarousel)
    @RequiresApi(Build.VERSION_CODES.R)
    val shopList = flowOf<List<Pair<String, String>>>(listofShops)
    var sectorList = flowOf<MutableList<BusinessTypeItem>>(listofBusiness.toMutableList())
    val designsList = flowOf<List<HomeDesignItem>>(listofDesigns)
    val campaignList = flowOf<List<HomeCampaignItem>>(listofHomeCampaigns)
    val stokSaleList = flowOf<List<HomeDesignItem>>(listofStokSale)
    val sectorNewsList = flowOf<List<SectorNewsItem>>(listofHomeSectorNews)

    val feedDashboardItems = flowOf<List<FeedDashboardItems>>(listofFeedDashboard)
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