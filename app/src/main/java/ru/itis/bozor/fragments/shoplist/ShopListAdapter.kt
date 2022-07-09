package ru.itis.bozor.fragments.shoplist

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.itis.bozor.R
import ru.itis.bozor.data.model.ShopListModel
import ru.itis.bozor.data.viewmodel.ShopListViewModel
import ru.itis.bozor.databinding.ShoplistItemBinding
import java.util.*
import kotlin.collections.ArrayList

interface ShopListActionListener {

    fun onShopListDelete(shopList: ShopListModel)

    fun onShopListDetails(shopList: ShopListModel)

    fun onShopListEdit(shopList: ShopListModel)
}

class ShopListAdapter(private val actionListener: ShopListActionListener):
    RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>(),
    View.OnClickListener {

    private var shopLists = emptyList<ShopListModel>()

    class ShopListViewHolder(val binding: ShoplistItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(shopListModel: ShopListModel) = with(binding) {
            itemView.tag = shopListModel
            ivShopListMore.tag = shopListModel
            tvShopListName.text = shopListModel.name
            tvShopListDescription.text = shopListModel.description
            ivShopListMore.setImageResource(R.drawable.ic_more)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ShoplistItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.ivShopListMore.setOnClickListener(this)

        return ShopListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
        holder.bind(shopLists[position])
    }

    fun setData(shopList: List<ShopListModel>) {
        this.shopLists = shopList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = shopLists.size

    override fun onClick(v: View) {
        val shopList = v.tag as ShopListModel
        when(v.id) {
            R.id.iv_shopList_more -> {
                showPopupMenu(v)
            }
            else -> {
                actionListener.onShopListDetails(shopList)
            }
        }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        val shopList = view.tag as ShopListModel

        popupMenu.menu.add(0, ID_DELETE, Menu.NONE,"Delete")
        popupMenu.menu.add(0, ID_EDIT, Menu.NONE, "Edit")

            popupMenu.setOnMenuItemClickListener {
            when(it.itemId) {
                ID_DELETE -> {
                    actionListener.onShopListDelete(shopList)
                }
                ID_EDIT -> {
                    actionListener.onShopListEdit(shopList)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popupMenu.show()
    }

    companion object {
        private const val ID_DELETE = 1
        private const val ID_EDIT = 2
    }



}