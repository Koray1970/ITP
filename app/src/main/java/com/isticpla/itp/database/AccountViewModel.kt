package com.isticpla.itp.database

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModel @Inject constructor(
    private val accounDAO: AccountDAO
) : ViewModel() {
    val getAccount = accounDAO.getAccount()
    //.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), typeOf<Account>())

    fun UpsertAccount(account: Account) {
        viewModelScope.launch {
            accounDAO.upsertAccount(account)
        }
    }
}