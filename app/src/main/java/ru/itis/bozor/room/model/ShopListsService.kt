package ru.itis.bozor.room.model

import com.github.javafaker.Faker
import java.util.*

typealias ShopListListener = (shopLists: List<ShopList>) -> Unit

class ShopListsService {

    private var shopLists = mutableListOf<ShopList>()
    private var listeners = mutableSetOf<ShopListListener>()

    init {
        val faker = Faker.instance()
        shopLists = (1..100).map { ShopList(
            id = it.toLong(),
            name = faker.name().name(),
            description = faker.company().name(),
        ) }.toMutableList()
    }

    fun getShopLists(): List<ShopList> {
        return shopLists
    }

    fun deleteAccount(shopList: ShopList) {
        val indexToDelete = shopLists.indexOfFirst { it.id == shopList.id }
        if (indexToDelete != -1) {
            shopLists.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveShopList(shopList: ShopList, moveBy: Int){
        val oldIndex = shopLists.indexOfFirst { it.id == shopList.id }
        if (oldIndex != -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= shopLists.size) return
        Collections.swap(shopLists, oldIndex, newIndex)
        notifyChanges()
    }

    fun addLister(listener: ShopListListener) {
        listeners.add(listener)
        listener.invoke(shopLists)
    }

    fun removeLister(listener: ShopListListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach{ it.invoke(shopLists) }
    }

}