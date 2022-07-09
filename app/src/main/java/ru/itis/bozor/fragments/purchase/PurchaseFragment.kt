package ru.itis.bozor.fragments.purchase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentPurchaseBinding

class PurchaseFragment: Fragment(R.layout.fragment_purchase) {
    private var _binding: FragmentPurchaseBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPurchaseBinding.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}