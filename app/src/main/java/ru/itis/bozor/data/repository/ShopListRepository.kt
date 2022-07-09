package ru.itis.bozor.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.switchMap
import kotlinx.coroutines.flow.Flow
import ru.itis.bozor.data.ShopListDao
import ru.itis.bozor.data.model.ShopListModel

class ShopListRepository(private val shopListDao: ShopListDao) {

    val readAllData: LiveData<List<ShopListModel>> = shopListDao.readAllData()

    fun addShopList(shopListModel: ShopListModel) {
        shopListDao.addShopList(shopListModel)
    }

    fun deleteShopList(shopListModel: ShopListModel) {
        shopListDao.deleteShopList(shopListModel)
    }

    fun updateShopList(shopListModel: ShopListModel) {
        shopListDao.updateShopList(shopListModel)
    }

}

//    fun findById(id: Int): ShopListModel {
//        return shopListDao.findById(id)
//    }


