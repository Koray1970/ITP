package com.isticpla.itp.database

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.isticpla.itp.Screen
import com.isticpla.itp.dummydata.*
import com.isticpla.itp.signup.AddYourBusiness
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModel @Inject constructor(
    private val accountDAO: AccountDAO
) : ViewModel() {
    var state by mutableStateOf(AccountListenerState())
        private set

    val getAccount = accountDAO.getAccount()
    val getNumberofAccount = accountDAO.getNumberofAccount()
    fun isAccountCreated(): Flow<Pair<Boolean, String?>> = flow {
        //viewModelScope.launch {
            while (true) {
                getAccount.collect {
                    if(it==null) {
                        emit(Pair(false, Screen.StartSelectCulture.route))
                    }
                    else {
                        if (it.cultureid == null) {
                            emit(Pair(false, Screen.StartSelectCulture.route))
                        } else {
                            if (it.phonenumber == null)
                                emit(Pair(false, Screen.SignUp.route))
                            else {
                                if (it.name == null || it.lastname == null || it.email == null || it.country == null)
                                    emit(Pair(false, Screen.CreateUserAccount.route))
                                else {
                                    if (it.companyname == null)
                                        emit(Pair(false, Screen.AddYourBusiness.route))
                                    else {
                                        if (it.sectors == null)
                                            emit(Pair(false, Screen.ChooseBusinessSalesAreas.route))
                                        else
                                            emit(Pair(true, null))
                                    }

                                }
                            }
                        }
                    }
                    delay(2000L)
                }

            }
        //}
    }


    private var _dbsectors: String = ""
    var dbsectors by mutableStateOf(_dbsectors)
        private set

    init {
        viewModelScope.launch {
            accountDAO.getAccountSectors().collect {
                dbsectors = it
                delay(1000L)

            }
        }
    }


    fun getSectorList(dbsectors: String): Flow<List<BusinessTypeItem>> {
        val gson = Gson()
        var lstibus = emptyArray<IBusinessTypeItem>()
        val lstbussiness = ArrayList<BusinessTypeItem>()
        viewModelScope.launch {
            while (true) {
                if (!dbsectors.isNullOrEmpty()) {
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
                }
                delay(1220L)
            }
        }
        lstbussiness.add(listofBusiness.first { a -> a.except })
        return flowOf(lstbussiness.toList())
    }

    fun getSectorList2(): Flow<List<BusinessTypeItem>> =
        flow<List<BusinessTypeItem>> {
            val gson = Gson()

            var lstibus = emptyArray<IBusinessTypeItem>()
            val lstbussiness = ArrayList<BusinessTypeItem>()
            while (true) {
                if (!dbsectors.isNullOrEmpty()) {
                    lstibus =
                        gson.fromJson(dbsectors, Array<IBusinessTypeItem>::class.java)
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
                    lstbussiness.add(listofBusiness.first { a -> a.except })
                }

                emit(lstbussiness.toList())
                if (lstbussiness.size > 1) break
                delay(1000L)
            }
        }

    fun UpsertAccount(account: Account) {
        viewModelScope.launch {
            accountDAO.upsertAccount(account)
        }
    }
}

data class AccountListenerState(
    var isLoading: Boolean = false,
    var isFinished: Boolean = false
)