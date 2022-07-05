package com.example.bozor.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bozor.R
import com.example.bozor.databinding.FragmentSignupBinding

class SignUpFragment: Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    val pref = activity?.getSharedPreferences("REGISTRATION",Context.MODE_PRIVATE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)
        val bMenu = activity!!.findViewById<View>(R.id.bNav)
        bMenu.visibility = View.GONE

        binding.btCreate.setOnClickListener {
            if(binding.tiSignUpUsername.editText?.text.toString() != ""){
                findNavController().navigate(R.id.action_signUpFragment_to_shopFragment)
                bMenu.visibility = View.VISIBLE

                pref?.edit {
                    putString("USERNAME", binding.tiSignUpUsername.editText?.text.toString())
                    commit()
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}