package ru.itis.bozor.room.model

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.itis.bozor.R
import ru.itis.bozor.databinding.ShoplistItemBinding

//interface ShopListActionListener {
//
//    fun onShopListMove(shopList: ShopList, moveBy: Int)
//
//    fun onShopListDelete(shopList: ShopList)
//
//    fun onShopListDetails(shopList: ShopList)

//}
//
//class ShopListsDiffCallBack(
//    private val oldList: List<ShopList>,
//    private val newList: List<ShopList>
//    ) : DiffUtil.Callback() {
//    override fun getOldListSize(): Int = oldList.size
//    override fun getNewListSize(): Int = newList.size
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldShopList = oldList[oldItemPosition]
//        val newShopList = newList[newItemPosition]
//        return oldShopList.id == newShopList.id
//    }
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        val oldShopList = oldList[oldItemPosition]
//        val newShopList = newList[newItemPosition]
//        return oldShopList == newShopList
//    }
//}
//
//class ShopListAdapter(
//    private val actionListener: ShopListActionListener
//    ) : RecyclerView.Adapter<ShopListAdapter.ShopListHolder>(), View.OnClickListener {
//
//    var shopLists: List<ShopList> = emptyList()
//        set(newValue) {
//            val diffUtilCallBack = ShopListsDiffCallBack(field, newValue)
//            val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
//            field = newValue
//            diffResult.dispatchUpdatesTo(this)
//        }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListHolder {
//
//        val inflater = LayoutInflater.from(parent.context)
//        val binding = ShoplistItemBinding.inflate(inflater, parent, false)
//
//        binding.root.setOnClickListener(this)
//        binding.ivShopListMore.setOnClickListener(this)
//
//        return ShopListHolder(binding)
//
//    }
//
//    override fun onBindViewHolder(holder: ShopListHolder, position: Int) {
//        val shopList = (shopLists[position])
//        with(holder.binding){
//            holder.itemView.tag = shopList
//            ivShopListMore.tag = shopList
//            tvShopListName.text = shopList.name
//            tvShopListDescription.text = shopList.description
//            ivShopListMore.setImageResource(R.drawable.ic_more)
//        }
//    }
//
//    override fun getItemCount(): Int = shopLists.size
//
//    override fun onClick(v: View) {
//        val shopList = v.tag as ShopList
//        when(v.id) {
//            R.id.iv_shopList_more -> {
//                showPopupMenu(v)
//            }
//            else -> {
//                actionListener.onShopListDetails(shopList)
//            }
//        }
//    }
//
//    private fun showPopupMenu(view: View) {
//        val popupMenu = PopupMenu(view.context, view)
//        val shopList = view.tag as ShopList
//        val position = shopLists.indexOfFirst { it.id == shopList.id }
//
//        popupMenu.menu.add(0, ID_MOVE_UP, Menu.NONE,"Move Up").apply {
//            isEnabled = position > 0
//        }
//        popupMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE,"Move Down").apply {
//            isEnabled = position < shopLists.size - 1
//        }
//        popupMenu.menu.add(0, ID_MOVE_DELETE, Menu.NONE,"Move Delete")
//
//        popupMenu.setOnMenuItemClickListener {
//            when(it.itemId) {
//                ID_MOVE_UP -> {
//                    actionListener.onShopListMove(shopList, -1)
//                }
//                ID_MOVE_DOWN -> {
//                    actionListener.onShopListMove(shopList, 1)
//                }
//                ID_MOVE_DELETE -> {
//                    actionListener.onShopListDelete(shopList)
//                }
//            }
//            return@setOnMenuItemClickListener true
//        }
//        popupMenu.show()
//    }
//
//    companion object {
//        private const val ID_MOVE_UP = 1
//        private const val ID_MOVE_DOWN = 2
//        private const val ID_MOVE_DELETE = 3
//    }
//
//    class ShopListHolder(
//        val binding: ShoplistItemBinding
//    ) : RecyclerView.ViewHolder(binding.root)
//
//}