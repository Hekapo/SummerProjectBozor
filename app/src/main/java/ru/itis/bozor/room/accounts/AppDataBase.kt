package ru.itis.bozor.room.accounts

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        AccountEntity::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getAccountsDao(): AccountsDao

}