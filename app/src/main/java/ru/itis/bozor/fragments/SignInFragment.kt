package ru.itis.bozor.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentSigninBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class SignInFragment: Fragment(R.layout.fragment_signin) {
    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!
    val pref = activity?.getSharedPreferences("REGISTRATION", Context.MODE_PRIVATE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSigninBinding.bind(view)
        val bMenu = activity!!.findViewById<View>(R.id.bNav)
        bMenu.visibility = View.GONE

        with(binding){
            btSignIn.setOnClickListener {
                checkField(tiSignInUsername)
                checkField(tiSignInPassword)
                if (checkField(tiSignInUsername) && checkField(tiSignInPassword) &&
                    (tiSignInUsername.editText?.text.toString() == initPref())){
                    findNavController().navigate(R.id.action_signInFragment_to_shopFragment)
                    bMenu.visibility = View.VISIBLE
                } else Snackbar.make(view,"Error",Snackbar.LENGTH_SHORT).show()
            }
            btSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
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

    private fun initPref(): String {
        return pref?.getString("USERNAME", "error").orEmpty()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}