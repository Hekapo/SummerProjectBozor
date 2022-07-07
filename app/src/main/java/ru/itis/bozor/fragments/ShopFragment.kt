package ru.itis.bozor.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentShopBinding


class ShopFragment: Fragment(R.layout.fragment_shop) {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShopBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}
