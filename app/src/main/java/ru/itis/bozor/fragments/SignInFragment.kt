package ru.itis.bozor.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentSigninBinding
import com.google.android.material.textfield.TextInputLayout
import ru.itis.bozor.fragments.Constants.APP_PREFERENCES
import ru.itis.bozor.fragments.Constants.PREF_PASSWORD
import ru.itis.bozor.fragments.Constants.PREF_USERNAME

class SignInFragment: Fragment(R.layout.fragment_signin) {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!
    private lateinit var preferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSigninBinding.bind(view)
        val bMenu = activity!!.findViewById<View>(R.id.bNav)
        bMenu.visibility = View.GONE

        preferences = activity!!.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        with(binding){
            btSignIn.setOnClickListener {
                checkField(tiSignInUsername)
                checkField(tiSignInPassword)
                if (checkField(tiSignInUsername) && checkField(tiSignInPassword) &&
                    checkCorrectUsername(tiSignInUsername) &&
                    checkCorrectPassword(tiSignInPassword)
                    ) {
                    findNavController().navigate(R.id.action_signInFragment_to_shopFragment)
                    bMenu.visibility = View.VISIBLE
                }
            }
            btSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
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

    private fun checkCorrectUsername(view: TextInputLayout): Boolean {
        val currentUsername = preferences.getString(PREF_USERNAME, "error").orEmpty()
        return if (view.editText?.text.toString() != currentUsername){
            view.error = getString(R.string.error_signIn_username)
            false
        } else {
            view.error = null
            true
        }
    }

    private fun checkCorrectPassword(view: TextInputLayout): Boolean {
        val currentPassword = preferences.getString(PREF_PASSWORD, "error").orEmpty()
        return if(view.editText?.text.toString() != currentPassword) {
            view.error = getString(R.string.error_signIn_password)
            false
        } else {
            view.error = null
            true
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}