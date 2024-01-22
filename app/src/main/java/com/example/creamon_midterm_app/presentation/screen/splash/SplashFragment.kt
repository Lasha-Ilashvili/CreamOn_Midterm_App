package com.example.creamon_midterm_app.presentation.screen.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.creamon_midterm_app.databinding.FragmentSplashBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels()


    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleNavigationEvents(event = it)
                }
            }
        }
    }

    private fun handleNavigationEvents(event: SplashViewModel.SplashUiEvent) {
        when (event) {
            is SplashViewModel.SplashUiEvent.NavigateToMainPage ->
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToMainPageFragment()
                )

            is SplashViewModel.SplashUiEvent.NavigateToWelcomePage ->
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToWelcomePageFragment()
                )
        }
    }
}