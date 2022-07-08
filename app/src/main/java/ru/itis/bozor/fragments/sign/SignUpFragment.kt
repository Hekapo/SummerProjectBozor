package ru.itis.bozor.fragments.sign

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentSignupBinding
import ru.itis.bozor.fragments.Constants.APP_PREFERENCES
import ru.itis.bozor.fragments.Constants.PREF_PASSWORD
import ru.itis.bozor.fragments.Constants.PREF_USERNAME

class SignUpFragment: Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferences: SharedPreferences


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)
        val bMenu = activity!!.findViewById<View>(R.id.bNav)
        bMenu.visibility = View.GONE
        preferences = activity!!.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        with(binding) {
            btCreate.setOnClickListener {
                checkPassword(tiSignUpPassword,tiSignUpConfirmPassword)
                checkField(tiSignUpUsername)
                checkField(tiSignUpPassword)
                checkField(tiSignUpConfirmPassword)
                checkField(tiEmail)
                if (checkField(tiSignUpUsername) &&
                    checkField(tiSignUpPassword) &&
                    checkField(tiSignUpConfirmPassword) &&
                    checkField(tiEmail) &&
                    checkPassword(tiSignUpPassword,tiSignUpConfirmPassword)) {
                    findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    bMenu.visibility = View.VISIBLE
                    preferences.edit {
                        putString(PREF_USERNAME, tiSignUpUsername.editText?.text.toString())
                        putString(PREF_PASSWORD, tiSignUpPassword.editText?.text.toString())
                        commit()
                    }
                }
            }
        }
    }

    private fun checkField(view: TextInputLayout): Boolean {
        return if (view.editText?.text.toString().isEmpty()) {
            view.error = getString(R.string.error_signUp)
            false
        } else {
            view.error = null
            true
        }
    }

    private fun checkPassword(view_1: TextInputLayout, view_2: TextInputLayout): Boolean {
        return if (view_1.editText?.text.toString() != view_2.editText?.text.toString()){
            view_1.error = getString(R.string.error_signUp_password)
            view_2.error = getString(R.string.error_signUp_password)
            false
        } else {
            view_1.error = null
            view_2.error = null
            true
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}