package ru.itis.bozor.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentSignupBinding

class SignUpFragment: Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    val pref = activity?.getSharedPreferences("REGISTRATION", Context.MODE_PRIVATE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)
        val bMenu = activity!!.findViewById<View>(R.id.bNav)
        bMenu.visibility = View.GONE

        with(binding) {
            btCreate.setOnClickListener {
                checkField(tiSignUpUsername)
                checkField(tiSignUpPassword)
                checkField(tiSignUpConfirmPassword)
                checkField(tiEmail)
                if (checkField(tiSignUpUsername) &&
                    checkField(tiSignUpPassword) &&
                    checkField(tiSignUpConfirmPassword) &&
                    checkField(tiEmail)) {
                    findNavController().navigate(R.id.action_signUpFragment_to_shopFragment)
                    bMenu.visibility = View.VISIBLE
                    pref?.edit {
                        putString("USERNAME", tiSignUpUsername.editText?.text.toString())
                        commit()
                    }
                }
            }
        }
    }

    private fun checkField(view: TextInputLayout): Boolean {
        if (view.editText?.text.toString().isEmpty()) {
            view.error = getString(R.string.error_signUp)
            return false
        } else {
            view.error = null
            return true
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}