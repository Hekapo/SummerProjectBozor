package ru.itis.bozor.room.model

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.itis.bozor.R
import ru.itis.bozor.databinding.ShoplistItemBinding

class ShopListHolder(
    private val binding: ShoplistItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind (shopList: ShopList) = with(binding){
        tvShopListName.text = shopList.name
        tvShopListDescription.text = shopList.description
        ivShopListMore.setImageResource(R.drawable.ic_more)
    }

}