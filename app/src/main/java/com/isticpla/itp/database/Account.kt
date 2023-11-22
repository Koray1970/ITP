package com.isticpla.itp.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import java.sql.Blob

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var cultureid: Int? = null,
    var areacode: String? = null,
    var phonenumber: String? = null,
    var contactapproved: Boolean = false,
    var contractapproved: Boolean = false,
    var name: String? = null,
    var lastname: String? = null,
    var email: String? = null,
    var birthdate: String? = null,
    var country: String? = null,
    var refcode: String? = null,
    var companylogo: String? = null,
    var companyname: String? = null,
    var employeeposition: Int? = null,
    var sectors: String? = null
)

@Dao
interface AccountDAO {
    @Upsert
    suspend fun upsertAccount(account: Account)

    @Query("SELECT * FROM accounts LIMIT 1")
    fun getAccount(): Flow<Account>

    @Query("SELECT sectors FROM accounts LIMIT 1")
    fun getAccountSectors(): Flow<String>
}
