package com.isticpla.itp.offerdetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isticpla.itp.dummydata.ProductFeatureItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class AdditionalProductDetails(
    val id: UUID = UUID.randomUUID(),
    val formitem: ProductFeatureItem,
    val txtvalue: MutableState<String> = mutableStateOf(""),
    var txtiserror:MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class OfferViewModel @Inject constructor() : ViewModel() {
    private var listofAdditionalProductDetail = mutableStateListOf<AdditionalProductDetails>()
    val additionalProductDetails =
        flowOf<MutableList<AdditionalProductDetails>>(listofAdditionalProductDetail)

    fun AddItemToAdditionalProductDetails(itm: AdditionalProductDetails) = viewModelScope.launch {
        listofAdditionalProductDetail.add(itm)
    }

    fun removeItemFromAdditionalProductDetails(itm: AdditionalProductDetails) =
        viewModelScope.launch {
            listofAdditionalProductDetail.remove(itm)
        }
}