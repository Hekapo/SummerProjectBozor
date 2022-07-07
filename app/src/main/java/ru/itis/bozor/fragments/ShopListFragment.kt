package ru.itis.bozor.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentShoplistBinding

class ShopListFragment: Fragment(R.layout.fragment_shoplist) {
    private var _binding: FragmentShoplistBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShoplistBinding.bind(view)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}