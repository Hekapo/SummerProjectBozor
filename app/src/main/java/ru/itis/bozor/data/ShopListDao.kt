package ru.itis.bozor.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.itis.bozor.data.model.ShopListModel

@Dao
interface ShopListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addShopList(shopList: ShopListModel)

    @Query("SELECT * FROM shopLists ORDER BY id ASC")
    fun readAllData(): LiveData<List<ShopListModel>>

}