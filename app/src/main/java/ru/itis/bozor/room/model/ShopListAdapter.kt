package ru.itis.bozor.room.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.bozor.databinding.ShoplistItemBinding

class ShopListAdapter(private val shopLists: List<ShopList>) : RecyclerView.Adapter<ShopListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListHolder = ShopListHolder(
        ShoplistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ShopListHolder, position: Int) {
        holder.bind(shopLists[position])
    }

    override fun getItemCount(): Int = shopLists.size
}