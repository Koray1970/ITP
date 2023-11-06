package com.isticpla.itp.dummydata

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ExpendedMenuSelectedCollectionItem<T>(
    val productFeatureItem: ProductFeatureItem,
    var collectionData: MutableList<T>
)

fun <T> ListofExpendedMenuItem() = mutableListOf<ExpendedMenuSelectedCollectionItem<T>>()
interface IExpendMenuReposity<T> {
    fun <T> GetSelectedItemCollection(): MutableList<ExpendedMenuSelectedCollectionItem<T>>
    fun <T> GetSelectedItemDataCollection(request: ProductFeatureItem): MutableList<T>
    fun <T> AddItemToSelectItemCollection(item: ExpendedMenuSelectedCollectionItem<T>)
    fun <T> RemoveDataFromSelectedItemCollectionList(itm: ProductFeatureItem)
    fun <T> RemoveDataItemFromSelectedItemCollectionData(citem: ProductFeatureItem, itm: T)
}

class ExpendMenuReposity<T>() : IExpendMenuReposity<T> {
    override fun <T> GetSelectedItemCollection(): MutableList<ExpendedMenuSelectedCollectionItem<T>> {
        return ListofExpendedMenuItem<T>()
    }

    override fun <T> GetSelectedItemDataCollection(request: ProductFeatureItem): MutableList<T> {
        return ListofExpendedMenuItem<T>().first { a -> a.productFeatureItem == request }.collectionData
    }

    override fun <T> AddItemToSelectItemCollection(item: ExpendedMenuSelectedCollectionItem<T>) {
        ListofExpendedMenuItem<T>().add(item)
    }

    override fun <T> RemoveDataFromSelectedItemCollectionList(itm: ProductFeatureItem) {
        ListofExpendedMenuItem<T>().removeIf { a -> a.productFeatureItem == itm }
    }

    override fun <T> RemoveDataItemFromSelectedItemCollectionData(
        citem: ProductFeatureItem,
        itm: T
    ) {
        var filterDataItem =
            ListofExpendedMenuItem<T>().filter { a -> a.productFeatureItem == citem }
        if (!filterDataItem.isNullOrEmpty()){
            filterDataItem.first().collectionData.removeIf { a->a==itm }
        }
    }
}

@HiltViewModel
class ExpendedMenuViewModel @Inject constructor() : ViewModel() {

}
