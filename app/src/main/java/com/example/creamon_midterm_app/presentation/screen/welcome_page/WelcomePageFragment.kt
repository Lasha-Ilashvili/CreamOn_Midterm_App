package com.example.creamon_midterm_app.presentation.screen.welcome_page

import androidx.navigation.fragment.findNavController
import com.example.creamon_midterm_app.databinding.FragmentWelcomePageBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment


class WelcomePageFragment :
    BaseFragment<FragmentWelcomePageBinding>(FragmentWelcomePageBinding::inflate) {

    override fun setListeners() {
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(
                WelcomePageFragmentDirections.actionWelcomePageFragmentToSignUpFragment()
            )
        }
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(
                WelcomePageFragmentDirections.actionWelcomePageFragmentToLogInFragment()
            )
        }
    }
}