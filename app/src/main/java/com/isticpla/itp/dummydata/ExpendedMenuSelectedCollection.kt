package com.isticpla.itp.dummydata

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExpendedMenuSelectedCollectionItem(
    val productFeatureItem: ProductFeatureItem,
    var collectionData: MutableList<Pair<Object, Object>>
)

var listofExpendedMenuItem = mutableListOf<ExpendedMenuSelectedCollectionItem>()

interface IExpendMenuReposity {
    fun GetSelectedItemCollection(): MutableList<ExpendedMenuSelectedCollectionItem>
    fun GetSelectedItemDataCollection(request: ProductFeatureItem): MutableList<Pair<Object, Object>>
    fun AddItemToSelectItemCollection(item: ProductFeatureItem)
    fun AddDataItemToSelectItemCollection(item: ProductFeatureItem, cdata: Pair<Object, Object>)
    fun RemoveDataFromSelectedItemCollectionList(itm: ProductFeatureItem)
    fun RemoveDataItemFromSelectedItemCollectionData(
        citem: ProductFeatureItem,
        itm: Pair<Object, Object>
    )
}

class ExpendMenuReposity @Inject constructor() : IExpendMenuReposity {
    override fun GetSelectedItemCollection(): MutableList<ExpendedMenuSelectedCollectionItem> {
        return listofExpendedMenuItem
    }

    override fun GetSelectedItemDataCollection(request: ProductFeatureItem): MutableList<Pair<Object, Object>> {
        return listofExpendedMenuItem.first { a -> a.productFeatureItem == request }.collectionData
    }

    override fun AddItemToSelectItemCollection(item: ProductFeatureItem) {
        listofExpendedMenuItem.add(
            ExpendedMenuSelectedCollectionItem(
                item,
                emptyList<Pair<Object, Object>>().toMutableList()
            )
        )
        Log.v("ExpendedMenuReposity", "${listofExpendedMenuItem.size}")
    }

    override fun AddDataItemToSelectItemCollection(
        item: ProductFeatureItem,
        cdata: Pair<Object, Object>
    ) {
        listofExpendedMenuItem.first { a -> a.productFeatureItem == item }.collectionData.add(
            cdata
        )
    }

    override fun RemoveDataFromSelectedItemCollectionList(itm: ProductFeatureItem) {
        listofExpendedMenuItem.removeIf { a -> a.productFeatureItem == itm }
    }

    override fun RemoveDataItemFromSelectedItemCollectionData(
        citem: ProductFeatureItem,
        itm: Pair<Object, Object>
    ) {
        var filterDataItem =
            listofExpendedMenuItem.filter { a -> a.productFeatureItem == citem }
        if (!filterDataItem.isNullOrEmpty()) {
            filterDataItem.first().collectionData.removeIf { a -> a == itm }
        }
    }
}

@HiltViewModel
class ExpendedMenuViewModel @Inject constructor(private val repo: ExpendMenuReposity) :
    ViewModel() {
    val listOfProductFeatures =
        flowOf<MutableList<ProductFeatureItem>>(listofProductFeature.toMutableList())

    //var listOfSelectedCollections= MutableStateFlow(mutableListOf<ExpendedMenuSelectedCollectionItem>())
    private val _uiStateSelectedItem =
        MutableStateFlow(mutableListOf<ExpendedMenuSelectedCollectionItem>())
    val uiStateSelectedItem: StateFlow<MutableList<ExpendedMenuSelectedCollectionItem>> =
        _uiStateSelectedItem

    init {
        viewModelScope.launch {
            while (true) {
                delay(1000L)
                _uiStateSelectedItem.value = listofExpendedMenuItem
            }
        }
    }

    var listOfSelectedCollections = flow<MutableList<ExpendedMenuSelectedCollectionItem>> {
        while (true) {
            delay(1000L)
            //println("GetSelectedItemCollection()")
            emit(listofExpendedMenuItem)
        }
    }
        .flowOn(Dispatchers.IO)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            0
        )
    //flowOf<MutableList<ExpendedMenuSelectedCollectionItem>>(repo.GetSelectedItemCollection())

    fun AddSelectedCollection(itm: ProductFeatureItem) = viewModelScope.launch {
        repo.AddItemToSelectItemCollection(itm)
    }

    fun AddDataIntoSelectedCollection(itm: ProductFeatureItem, data: Pair<Object, Object>) =
        viewModelScope.launch {
            repo.AddDataItemToSelectItemCollection(itm, data)
        }

    fun RemoveDataItemFromSelectedCollection(itm: ProductFeatureItem, data: Pair<Object, Object>) =
        viewModelScope.launch {
            repo.RemoveDataItemFromSelectedItemCollectionData(itm, data)
        }

    fun RemoveSelectedCollection(itm: ProductFeatureItem) =
        viewModelScope.launch {
            repo.RemoveDataFromSelectedItemCollectionList(itm)
        }
}
