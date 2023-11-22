package com.isticpla.itp.database

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.isticpla.itp.dummydata.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModel @Inject constructor(
    private val accountDAO: AccountDAO
) : ViewModel() {
    val getAccount = accountDAO.getAccount()


    fun getSectorList(dbsectors: String): Flow<List<BusinessTypeItem>> {
        val gson = Gson()
        var lstibus = emptyArray<IBusinessTypeItem>()
        val lstbussiness = ArrayList<BusinessTypeItem>()
        lstibus = gson.fromJson(dbsectors, Array<IBusinessTypeItem>::class.java)
        if (lstibus.isNotEmpty()) {
            lstibus.forEach { b ->
                lstbussiness.add(
                    BusinessTypeItem(
                        id = b.id,
                        icon = b.icon,
                        label = b.label,
                        except = b.except
                    )
                )
            }
        }

        lstbussiness.add(listofBusiness.first { a->a.except })
        return flowOf(lstbussiness.toList())
    }


    fun UpsertAccount(account: Account) {
        viewModelScope.launch {
            accountDAO.upsertAccount(account)
        }
    }
}