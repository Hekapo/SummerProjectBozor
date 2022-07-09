package ru.itis.bozor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.itis.bozor.data.model.ShopListModel

@Database(entities = [ShopListModel::class], version = 1)
abstract class ShopListDataBase : RoomDatabase() {

    abstract fun shopListDao(): ShopListDao

    companion object{
        @Volatile
        private var INSTANCE: ShopListDataBase? = null

        fun getDataBase(context: Context): ShopListDataBase {
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopListDataBase::class.java,
                    "shopLists"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}