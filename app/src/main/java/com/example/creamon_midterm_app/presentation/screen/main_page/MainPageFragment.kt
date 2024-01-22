package com.example.creamon_midterm_app.presentation.screen.main_page

import android.util.Log.d
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.creamon_midterm_app.databinding.FragmentMainPageBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import com.example.creamon_midterm_app.presentation.event.store_items.StoreItemsEvent
import com.example.creamon_midterm_app.presentation.extension.showToast
import com.example.creamon_midterm_app.presentation.state.store_items.StoreItemsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainPageFragment : BaseFragment<FragmentMainPageBinding>(FragmentMainPageBinding::inflate) {

    private val viewModel: MainPageViewModel by viewModels()


    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.storeItems.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(storeItemsState: StoreItemsState) {
        when {
            storeItemsState.isLoading -> {
                // Show loading indicator
            }

            storeItemsState.result != null -> {
                d("CheckList", storeItemsState.result.toString())
                viewModel.onEvent(StoreItemsEvent.ResetErrorMessage)
            }

            storeItemsState.errorMessage != null -> {
                binding.root.showToast(storeItemsState.errorMessage)
            }
        }
    }

}