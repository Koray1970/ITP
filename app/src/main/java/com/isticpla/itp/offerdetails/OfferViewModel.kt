package com.isticpla.itp.offerdetails

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.isticpla.itp.dummydata.ProductFeatureItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import java.util.UUID
import javax.inject.Inject

data class AdditionalProductDetails(
    val id: UUID = UUID.randomUUID(),
    val formitem: ProductFeatureItem,
    val value: String = ""
)

@HiltViewModel
class OfferViewModel @Inject constructor() : ViewModel() {
    val additionalProductDetails =
        flowOf<MutableList<AdditionalProductDetails>>(mutableStateListOf<AdditionalProductDetails>())
}