package com.isticpla.itp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Account::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDAO(): AccountDAO
}