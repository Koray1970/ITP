package com.isticpla.itp.database

import android.util.Log
import androidx.collection.emptyFloatList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.isticpla.itp.dummydata.BusinessTypeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf


@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModel @Inject constructor(
    private val accounDAO: AccountDAO
) : ViewModel() {
    val getAccount = accounDAO.getAccount()
    val getSectorList = emptyFlow<MutableList<BusinessTypeItem>>()

    //.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), typeOf<Account>())
    fun getAccountSectors() = flow<MutableList<BusinessTypeItem>> {
        viewModelScope.launch {
            val gson = Gson()
            val getsectorsdb = accounDAO.getAccountSectors().collectLatest {
                if (!it.isNullOrEmpty()) {
                    Log.v("MainActivity2", it)
                    val sectorstr = gson.fromJson(it, Array<BusinessTypeItem>::class.java)
                    if (sectorstr.isNotEmpty()) {
                        sectorstr.forEach { b ->
                            getSectorList.first { i -> i.add(b) }
                        }
                    }
                }
            }
        }
    }

    fun UpsertAccount(account: Account) {
        viewModelScope.launch {
            accounDAO.upsertAccount(account)
        }
    }
}