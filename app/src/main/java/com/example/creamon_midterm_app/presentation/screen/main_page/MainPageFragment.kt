package com.example.creamon_midterm_app.presentation.screen.main_page

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.creamon_midterm_app.databinding.FragmentMainPageBinding
import com.example.creamon_midterm_app.presentation.adapter.main_page.MainPageRecyclerAdapter
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import com.example.creamon_midterm_app.presentation.event.store_items.StoreItemsEvent
import com.example.creamon_midterm_app.presentation.extension.showToast
import com.example.creamon_midterm_app.presentation.state.store_items.StoreItemsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainPageFragment : BaseFragment<FragmentMainPageBinding>(FragmentMainPageBinding::inflate) {

    private val viewModel: MainPageViewModel by viewModels()

    override fun setListeners() {
        binding.tvLogOut.setOnClickListener {
            viewModel.onEvent(StoreItemsEvent.LogOut)
        }
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.storeItems.collect {
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

    private fun handleState(storeItemsState: StoreItemsState) {
        binding.progressBar.visibility =
            if (storeItemsState.isLoading) View.VISIBLE else View.GONE

        storeItemsState.data?.let {
            binding.rvParent.adapter = MainPageRecyclerAdapter().apply {
                itemOnClick = ::navigateToItemPage
                setParentData(it.categories)
            }
        }

        storeItemsState.errorMessage?.let {
            binding.root.showToast(storeItemsState.errorMessage)
            viewModel.onEvent(StoreItemsEvent.ResetErrorMessage)
        }
    }

    private fun navigateToItemPage(id: Int) {
        findNavController().navigate(
            MainPageFragmentDirections.actionMainPageFragmentToItemPageFragment(id = id)
        )
    }

    private fun handleNavigationEvents(event: MainPageViewModel.MainPageUiEvent) {
        when (event) {
            is MainPageViewModel.MainPageUiEvent.NavigateBackToWelcomePage -> {
                findNavController().navigate(
                    MainPageFragmentDirections.actionMainPageFragmentToWelcomePageFragment()
                )
            }
        }
    }
}