package ru.itis.bozor.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.itis.bozor.data.ShopListDataBase
import ru.itis.bozor.data.model.ShopListModel
import ru.itis.bozor.data.repository.ShopListRepository

class ShopListViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<ShopListModel>>
    private val repository: ShopListRepository

    init {
        val shopListDao = ShopListDataBase.getDataBase(application).shopListDao()
        repository = ShopListRepository(shopListDao)
        readAllData = repository.readAllData
    }

    fun addShopList(shopListModel: ShopListModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addShopList(shopListModel)
        }
    }

}