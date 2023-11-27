package com.isticpla.itp.offerdetails

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isticpla.itp.dummydata.ProductFeatureItem
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
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
    private var listofAdditionalProductDetail = mutableStateListOf<AdditionalProductDetails>()
    val additionalProductDetails =
        flowOf<MutableList<AdditionalProductDetails>>(listofAdditionalProductDetail)
    private var listofMediaFiles= context.fileList().toMutableList().toMutableStateList()
    val getFileFromLocalDir: Flow<MutableList<String>> = flow{
        while (true){
            emit(context.fileList().sortedDescending().toMutableStateList())
            delay(1200L)
        }
    }
    val getFileFromSharedDir: Flow<MutableList<Uri>> = flow{
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
                selection,
                selectionArgs,
                sortOrder
            )

            query?.use{cursor->
                val listofimage= mutableListOf<Uri>()
                while (cursor.moveToNext()){
                    listofimage.add(ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID))
                    ))
                }
                emit(listofimage)
            }

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

}