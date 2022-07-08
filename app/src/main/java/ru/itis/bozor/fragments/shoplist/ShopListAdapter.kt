package ru.itis.bozor.fragments.shoplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.bozor.data.model.ShopListModel
import ru.itis.bozor.databinding.ShoplistItemBinding

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

    private var shopLists = emptyList<ShopListModel>()

    class ShopListViewHolder(val binding: ShoplistItemBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder = ShopListViewHolder(
        ShoplistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        val currentItem = shopLists[position]
        with(holder.binding) {
            tvShopListName.text = currentItem.name
            tvShopListDescription.text = currentItem.description
        }
    }

    fun setData(shopList: List<ShopListModel>) {
        this.shopLists = shopList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = shopLists.size

}