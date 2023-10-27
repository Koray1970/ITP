package com.isticpla.itp.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.isticpla.itp.dummydata.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewMode @Inject constructor() : ViewModel() {
    @RequiresApi(Build.VERSION_CODES.R)
    val shopList = flowOf<List<Pair<String, String>>>(listofShops)
    val sectorList = flowOf<List<BusinessTypeItem>>(listofBusiness)
    val designsList = flowOf<List<HomeDesignItem>>(listofDesigns)
    val campaignList = flowOf<List<HomeCampaignItem>>(listofHomeCampaigns)
    val stokSaleList = flowOf<List<HomeDesignItem>>(listofStokSale)
    val sectorNewsList = flowOf<List<SectorNewsItem>>(listofHomeSectorNews)
}