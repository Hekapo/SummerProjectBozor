package com.example.bozor.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bozor.R
import com.example.bozor.databinding.FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}