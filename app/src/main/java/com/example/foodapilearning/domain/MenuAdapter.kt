package com.example.foodapilearning.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapilearning.R
import com.example.foodapilearning.data.response.MealsItem
import com.example.foodapilearning.databinding.ItemMenuRowBinding

//menerima list meals item
class MenuAdapter(private val listData: ArrayList<MealsItem?>?): RecyclerView.Adapter<MenuAdapter.ListViewHolder>() {
    //private var listData = ArrayList<MealsItem>()
    var onItemClick: ((MealsItem) -> Unit)? = null

    fun setData(newListData: List<MealsItem>?) {
        if (newListData == null) return
        listData?.clear()
        listData?.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_menu_row, parent, false))

    override fun getItemCount(): Int = listData?.size!!

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData?.get(position)
        if (data != null) {
            holder.bind(data)
            holder.itemView.setOnClickListener { listData?.get(holder.adapterPosition)?.let { it1 ->
                onItemClickCallback.onItemClicked(
                    it1
                )
            } }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MealsItem)
    }

    //bind data ke dalam recyclerview
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMenuRowBinding.bind(itemView)
        fun bind(data: MealsItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.strMealThumb)
                    .into(imgItemPhoto)
                tvItemName.text = data.strMeal
                tvItemDescription.text = data.strMealThumb
            }
        }

        init {
            binding.root.setOnClickListener {
                listData?.get(adapterPosition)?.let { it1 -> onItemClick?.invoke(it1) }
            }
        }
    }
}