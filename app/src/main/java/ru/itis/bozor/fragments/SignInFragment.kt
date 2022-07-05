package ru.itis.bozor.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.itis.bozor.R
import ru.itis.bozor.databinding.FragmentSigninBinding
import com.google.android.material.snackbar.Snackbar

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
                if (tiSignInUsername.editText?.text.toString() == initPref()){
                    Snackbar.make(view,initPref(),Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_signInFragment_to_shopFragment)
                    bMenu.visibility = View.VISIBLE
                } else Snackbar.make(view,"Error",Snackbar.LENGTH_SHORT).show()
            }
            btSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
            }
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