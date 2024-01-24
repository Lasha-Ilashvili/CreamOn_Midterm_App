package com.example.creamon_midterm_app.presentation.screen.sign_up

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.creamon_midterm_app.databinding.FragmentSignUpBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import com.example.creamon_midterm_app.presentation.event.sign_up.SignUpEvent
import com.example.creamon_midterm_app.presentation.extension.showToast
import com.example.creamon_midterm_app.presentation.state.authentication.AuthenticationState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun setListeners() {
        binding.btnSignUp.setOnClickListener {
            signUp()
        }
        binding.tvLogIn.setOnClickListener {
            findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
            )
        }
    }

    private fun signUp() {
        viewModel.onEvent(
            SignUpEvent.SignUp(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
            )
        )
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.signUpState.collect {
                    handleState(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    private fun handleState(signUpState: AuthenticationState) {
        when {
            signUpState.isLoading -> {
                // Show loading indicator
            }

            signUpState.result != null -> {
                binding.root.showToast(
                    "User: " + (signUpState.result.user?.email ?: "")
                            + " signed up successfully"
                )
                viewModel.onEvent(SignUpEvent.ResetErrorMessage)
            }

            signUpState.errorMessage != null -> {
                binding.root.showToast(signUpState.errorMessage)
            }
        }
    }

    private fun handleNavigationEvents(event: SignUpViewModel.SignUpUiEvent) {
        when (event) {
            is SignUpViewModel.SignUpUiEvent.NavigateBackToLogIn -> {
                val resultBundle = Bundle().apply {
                    putString("email", event.email)
                    putString("password", event.password)
                }

                parentFragmentManager.setFragmentResult("requestKey", resultBundle)

                findNavController().navigate(
                    SignUpFragmentDirections.actionSignUpFragmentToLogInFragment()
                )
            }
        }
    }
}