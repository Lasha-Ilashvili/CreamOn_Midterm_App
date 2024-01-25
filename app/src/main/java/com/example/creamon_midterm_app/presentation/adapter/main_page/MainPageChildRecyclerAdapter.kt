package com.example.creamon_midterm_app.presentation.adapter.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.creamon_midterm_app.databinding.MainPageItemBinding
import com.example.creamon_midterm_app.domain.model.store_items.StoreItem
import com.example.creamon_midterm_app.presentation.extension.loadImage

class MainPageChildRecyclerAdapter :
    RecyclerView.Adapter<MainPageChildRecyclerAdapter.MainPageItemViewHolder>() {

    private lateinit var storeItemList: List<StoreItem>

    var itemOnChildClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageItemViewHolder {
        return MainPageItemViewHolder(
            MainPageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainPageItemViewHolder, position: Int) {
        holder.bind()
    }

    fun setChildData(newList: List<StoreItem>) {
        storeItemList = newList
    }

    override fun getItemCount(): Int = storeItemList.size


    inner class MainPageItemViewHolder(private val binding: MainPageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val id = storeItemList[adapterPosition].id ?: -1
                itemOnChildClick?.invoke(id)
            }
        }

        fun bind() {
            val storeItem = storeItemList[adapterPosition]

            with(binding) {
                ivStoreItem.loadImage(storeItem.image)
                tvTitle.text = storeItem.title
                tvPrice.text = storeItem.price.toString()
            }
        }
    }
}