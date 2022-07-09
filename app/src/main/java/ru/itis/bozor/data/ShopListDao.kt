package ru.itis.bozor.data

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.itis.bozor.data.model.ShopListModel

@Dao
interface ShopListDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addShopList(shopList: ShopListModel)

    @Update
    fun updateShopList(shopList: ShopListModel)

    @Delete
    fun deleteShopList(shopList: ShopListModel)

    @Query("SELECT * FROM shopLists ORDER BY id ASC")
    fun readAllData(): LiveData<List<ShopListModel>>

}

//    @Query("SELECT * FROM shopLists WHERE id = :id")
//    fun findById(id: Int): ShopListModel