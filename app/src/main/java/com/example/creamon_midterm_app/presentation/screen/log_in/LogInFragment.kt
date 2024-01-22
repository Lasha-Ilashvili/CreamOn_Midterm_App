package com.example.creamon_midterm_app.presentation.screen.log_in

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.creamon_midterm_app.databinding.FragmentLogInBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import com.example.creamon_midterm_app.presentation.event.log_in.LogInEvent
import com.example.creamon_midterm_app.presentation.extension.showToast
import com.example.creamon_midterm_app.presentation.state.authentication.AuthenticationState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()

    override fun setUp() {
        setFragmentResultListener("requestKey") { _, bundle ->
            val email = bundle.getString("email")
            binding.etLoginEmail.setText(email)

            val password = bundle.getString("password")
            binding.etLoginPassword.setText(password)
        }
    }

    override fun setListeners() {
        binding.btnLogin.setOnClickListener {
            logIn()
        }
        binding.btnLoginSignUp.setOnClickListener {
            findNavController().navigate(
                LogInFragmentDirections.actionLogInFragmentToSignUpFragment()
            )
        }
    }

    private fun logIn() {
        viewModel.onEvent(
            LogInEvent.LogIn(
                binding.etLoginEmail.text.toString(),
                binding.etLoginPassword.text.toString(),
            )
        )
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logInState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(logInState: AuthenticationState) {
        when {
            logInState.isLoading -> {
                // Show loading indicator
            }

            logInState.result != null -> {
                binding.root.showToast(
                    "User: " + (logInState.result.user?.email ?: "")
                            + " logged in successfully"
                )
                viewModel.onEvent(LogInEvent.ResetErrorMessage)
            }

            logInState.errorMessage != null -> {
                binding.root.showToast(logInState.errorMessage)
            }
        }
    }
}
/*


    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collect {
                handleNavigationEvents(event = it)
            }
        }
    }
}



private fun handleNavigationEvents(event: SignUpUiEvent) {
    when (event) {
        is SignUpUiEvent.NavigateBackToLogIn -> {
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
}*/