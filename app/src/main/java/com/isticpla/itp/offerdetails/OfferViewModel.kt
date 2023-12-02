package com.isticpla.itp.offerdetails

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.isticpla.itp.dummydata.ProductFeatureItem
import com.isticpla.itp.dummydata.ServiceContentType
import com.isticpla.itp.dummydata.listOfServiceContentType
import com.isticpla.itp.dummydata.listOfServiceType
import com.isticpla.itp.dummydata.listofMyStore
import com.isticpla.itp.dummydata.listofShops
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

data class AdditionalProductDetails(
    val id: UUID = UUID.randomUUID(),
    val formitem: ProductFeatureItem,
    val txtvalue: MutableState<String> = mutableStateOf(""),
    var txtiserror: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class OfferViewModel @Inject constructor(@ApplicationContext private val context: Context) :
    ViewModel() {
    val gson = Gson()
    private var selectedMediaFiles = mutableStateListOf<Uri>()
    val getSelectedMediaFiles = flowOf<MutableList<Uri>>(selectedMediaFiles)
    private var listofAdditionalProductDetail = mutableStateListOf<AdditionalProductDetails>()
    val additionalProductDetails =
        flowOf<MutableList<AdditionalProductDetails>>(listofAdditionalProductDetail)
    private var listofMediaFiles = context.fileList().toMutableList().toMutableStateList()
    val getFileFromLocalDir: Flow<MutableList<String>> = flow {
        while (true) {
            emit(context.fileList().sortedDescending().toMutableStateList())
            delay(1200L)
        }
    }

    fun AddItemToAdditionalProductDetails(itm: AdditionalProductDetails) = viewModelScope.launch {
        listofAdditionalProductDetail.add(itm)
    }

    fun removeItemFromAdditionalProductDetails(itm: AdditionalProductDetails) =
        viewModelScope.launch {
            listofAdditionalProductDetail.remove(itm)
        }

    fun AddItemToSelectedMediaFiles(uri: Uri) = viewModelScope.launch {
        selectedMediaFiles.add(uri)
    }

    val getServiceTypes = flowOf<List<Pair<String, String>>>(listOfServiceType)
    private val firstFilter = listOfServiceType.first().first


    private val _serviceContentTypes = MutableStateFlow(listOf<ServiceContentType>())
    val serviceContentTypes: StateFlow<List<ServiceContentType>> get() = _serviceContentTypes

    private val _expendedServiceContents = MutableStateFlow(mutableListOf<Pair<String, String>>())
    val expendedServiceContents: StateFlow<MutableList<Pair<String, String>>> get() = _expendedServiceContents

    init {
        getFakeServiceContents()
    }

    private fun getFakeServiceContents() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                _expendedServiceContents.emit(mutableListOf<Pair<String, String>>())
            }
        }
    }

    fun onServiceSelected(filt: String) {
        expendedServiceContents.value.clear()
        listOfServiceContentType.filter { a -> a.typeid == filt }.forEach { a ->
            _expendedServiceContents.value.add(Pair<String, String>(a.id, a.value))
        }
    }

    val getSupplierList = flow<List<String>> {
        val list = mutableListOf<String>()
        listofMyStore.forEach { s ->
            list.add(s.title)
        }
        emit(list.toList())
    }

    /*val serviceContentDB = flowOf<List<ServiceContentType>>(listOfServiceContentType)
    private var _serviceContentList = mutableStateListOf<Pair<String, String>>()
    val getServiceContentList =
        flowOf<MutableList<Pair<String, String>>>(_serviceContentList)

    fun setServiceContents(filt: MutableState<String>): Flow<MutableList<Pair<String, String>>> =
        flow {
            while (true) {
                _serviceContentList = mutableStateListOf<Pair<String, String>>()
                if (!filt.value.isNullOrEmpty())
                    listOfServiceContentType.filter { a -> a.typeid == filt.value }
                        .forEach { a ->
                            _serviceContentList.add(Pair(a.id, a.value))
                        }
                emit(_serviceContentList)
                delay(1200L)
            }
        }*/
}
/*val getFileFromSharedDir: Flow<MutableList<Uri>> = flow{
        val listofimage= mutableListOf<Uri>()
        while (true){
            val collection=
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    MediaStore.Images.Media.getContentUri(
                        MediaStore.VOLUME_EXTERNAL
                    )
                } else {
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                }
            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media.MIME_TYPE,
                MediaStore.Images.Media.SIZE
            )
            val selection = "${MediaStore.Images.Media.MIME_TYPE} = ?"
            val selectionArgs = arrayOf("image/jpeg","image/x-png")

            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} ASC"
            val query = context.contentResolver.query(
                collection,
                projection,
                null,
                null,
                sortOrder
            )

            query?.use{cursor->

                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                while (cursor.moveToNext()){
                    val id = cursor.getLong(idColumn)
                    val contentUri: Uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                    listofimage.add(contentUri)
                }
                emit(listofimage)
            }

            delay(2200L)
        }
    }*/