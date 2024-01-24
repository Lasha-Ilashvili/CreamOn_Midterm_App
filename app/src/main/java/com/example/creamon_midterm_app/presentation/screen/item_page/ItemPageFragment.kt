package com.example.creamon_midterm_app.presentation.screen.item_page

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.creamon_midterm_app.databinding.FragmentItemPageBinding
import com.example.creamon_midterm_app.presentation.base.BaseFragment
import com.example.creamon_midterm_app.presentation.event.store_item.StoreItemEvent
import com.example.creamon_midterm_app.presentation.extension.loadImage
import com.example.creamon_midterm_app.presentation.extension.showToast
import com.example.creamon_midterm_app.presentation.state.store_item.StoreItemState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ItemPageFragment : BaseFragment<FragmentItemPageBinding>(FragmentItemPageBinding::inflate) {

    private val args: ItemPageFragmentArgs by navArgs()

    private val viewModel: ItemPageViewModel by viewModels()

    override fun setUp() {
        binding.ivBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.onEvent(StoreItemEvent.SetItem(args.id))
    }

    override fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.storeItem.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(storeItemState: StoreItemState) {
        storeItemState.data?.let {
            with(binding) {
                ivItem.loadImage(it.image)
                tvTitle.text = it.title
                tvCurrentAmount.text = it.price.toString()
                tvDescription.text = it.description
            }
        }

        storeItemState.errorMessage?.let {
            binding.root.showToast(storeItemState.errorMessage)
            viewModel.onEvent(StoreItemEvent.ResetErrorMessage)
        }
    }
}