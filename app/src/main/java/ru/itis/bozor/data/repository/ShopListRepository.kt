package ru.itis.bozor.data.repository

import androidx.lifecycle.LiveData
import ru.itis.bozor.data.ShopListDao
import ru.itis.bozor.data.model.ShopListModel

class ShopListRepository(private val shopListDao: ShopListDao) {

    val readAllData: LiveData<List<ShopListModel>> = shopListDao.readAllData()

    fun addShopList(shopListModel: ShopListModel) {
        shopListDao.addShopList(shopListModel)
    }

}