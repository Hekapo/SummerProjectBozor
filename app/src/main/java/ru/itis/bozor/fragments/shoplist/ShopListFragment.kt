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

class ShopListFragment: Fragment(R.layout.fragment_shoplist) {
    private var _binding: FragmentShoplistBinding? = null
    private val binding get() = _binding!!
    private lateinit var shopListViewModel: ShopListViewModel
    private lateinit var adapter: ShopListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShoplistBinding.bind(view)

        adapter = ShopListAdapter()
        binding.rwList.layoutManager = LinearLayoutManager(requireContext())
        binding.rwList.adapter = adapter

        shopListViewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        shopListViewModel.readAllData.observe(viewLifecycleOwner, Observer { shopList ->
            adapter.setData(shopList)
        })

        binding.fabAdd.setOnClickListener {
            showDialog()
        }

    }

    private fun insertDataToDataBase(name: String, description: String) {

        if(name.isNotBlank()){
            val shopList = ShopListModel(0, name, description)
            shopListViewModel.addShopList(shopList)
            Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDialog() {
        val dialogBinding = ShoplistAddBinding.inflate(layoutInflater)
        val listener = DialogInterface.OnClickListener { _, which ->
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val name = dialogBinding.etName.text.toString()
                    val description = dialogBinding.etDescription.text.toString()
                    insertDataToDataBase(name, description)
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("New list")
            .setView(dialogBinding.root)
            .setPositiveButton("Create", listener)
            .setNegativeButton("Cancel", listener)
            .create()
        dialog.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}