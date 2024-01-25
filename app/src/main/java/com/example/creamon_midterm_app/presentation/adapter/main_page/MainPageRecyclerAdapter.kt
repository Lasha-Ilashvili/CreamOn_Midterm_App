package com.example.creamon_midterm_app.presentation.adapter.main_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.creamon_midterm_app.databinding.MainPageItemsBinding
import com.example.creamon_midterm_app.domain.model.store_items.Category

class MainPageRecyclerAdapter :
    RecyclerView.Adapter<MainPageRecyclerAdapter.MainPageItemsViewHolder>() {

    private lateinit var categoryList: List<Category>

    var itemOnClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageItemsViewHolder {
        return MainPageItemsViewHolder(
            MainPageItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainPageItemsViewHolder, position: Int) {
        holder.bind()
    }

    fun setParentData(newList: List<Category>) {
        categoryList = newList
    }

    override fun getItemCount(): Int = categoryList.size


    inner class MainPageItemsViewHolder(private val binding: MainPageItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val category = categoryList[adapterPosition]

            with(binding) {
                tvTitle.text = category.title

                rvChild.adapter = MainPageChildRecyclerAdapter().apply {
                    itemOnChildClick = {
                        itemOnClick?.invoke(it)
                    }
                    setChildData(category.items)
                }
            }
        }
    }
}