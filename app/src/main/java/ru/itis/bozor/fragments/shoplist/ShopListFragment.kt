package ru.itis.bozor.fragments.shoplist

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ru.itis.bozor.R
import ru.itis.bozor.data.model.ShopListModel
import ru.itis.bozor.data.viewmodel.ShopListViewModel
import ru.itis.bozor.databinding.FragmentShoplistBinding
import ru.itis.bozor.databinding.ShoplistAddBinding
import ru.itis.bozor.databinding.ShoplistUpdateBinding

class ShopListFragment: Fragment(R.layout.fragment_shoplist) {
    private var _binding: FragmentShoplistBinding? = null
    private val binding get() = _binding!!
    private lateinit var shopListViewModel: ShopListViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShoplistBinding.bind(view)

        adapter = ShopListAdapter(object : ShopListActionListener {

            override fun onShopListDelete(shopList: ShopListModel) {
                shopListViewModel.deleteShopList(shopList)
            }
            override fun onShopListEdit(shopList: ShopListModel) {
                showDialogUpdate(shopList)
            }
            override fun onShopListDetails(shopList: ShopListModel) {
                Toast.makeText(requireContext(), shopList.name, Toast.LENGTH_SHORT).show()
            }
        })

        binding.rwList.layoutManager = LinearLayoutManager(requireContext())
        binding.rwList.adapter = adapter

        shopListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        shopListViewModel.readAllData.observe(viewLifecycleOwner, Observer { shopList ->
            adapter.setData(shopList)
        })

        binding.fabAdd.setOnClickListener {
            showDialogAdd()
        }

    }


    private fun showDialogAdd() {
        val dialogBinding = ShoplistAddBinding.inflate(layoutInflater)
        val dialogAdd = AlertDialog.Builder(requireContext())
            .setTitle("New list")
            .setView(dialogBinding.root)
            .setPositiveButton("Create", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialogAdd.setOnShowListener {
            dialogBinding.etAddName.requestFocus()
            dialogAdd.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val name = dialogBinding.etAddName.text.toString()
                val description = dialogBinding.etAddDescription.text.toString()
                if(name.isNotBlank()) {
                    val shopList = ShopListModel(0, name, description)
                    shopListViewModel.addShopList(shopList)
                    dialogAdd.dismiss()
                } else {
                    dialogBinding.etAddName.error = "Please enter the name"
                    return@setOnClickListener
                }
            }
        }
        dialogAdd.show()
    }

    private fun showDialogUpdate(shopListModel: ShopListModel) {
        val dialogBinding = ShoplistUpdateBinding.inflate(layoutInflater)
        dialogBinding.etUpdateName.setText(shopListModel.name)
        dialogBinding.etUpdateDescription.setText(shopListModel.description)
        val dialogUpdate = AlertDialog.Builder(requireContext())
            .setTitle("Rename list")
            .setView(dialogBinding.root)
            .setPositiveButton("Ok", null)
            .setNegativeButton("Cancel", null)
            .create()

        dialogUpdate.setOnShowListener {
            dialogBinding.etUpdateName.requestFocus()
            dialogUpdate.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener {
                val newName = dialogBinding.etUpdateName.text.toString()
                val newDescription = dialogBinding.etUpdateDescription.text.toString()
                if(newName.isNotBlank()) {
                    val shopList = ShopListModel(shopListModel.id, newName, newDescription)
                    shopListViewModel.updateShopList(shopList)
                    dialogUpdate.dismiss()
                } else {
                    dialogBinding.etUpdateName.error = "Please enter the name"
                    return@setOnClickListener
                }
            }
        }
        dialogUpdate.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}