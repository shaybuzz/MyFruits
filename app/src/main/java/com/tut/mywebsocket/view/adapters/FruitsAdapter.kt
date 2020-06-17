package com.tut.mywebsocket.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tut.mywebsocket.databinding.FruitItemLayoutBinding
import com.tut.mywebsocket.model.FruitItem

class FruitsAdapter : RecyclerView.Adapter<FruitsAdapter.FruitViewHolder>() {

    private var fruits: MutableList<FruitItem> = arrayListOf()

    inner class FruitViewHolder(val binding: FruitItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val binding =
            FruitItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FruitViewHolder((binding))
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.binding.fruit = fruits.get(position)
        holder.binding.executePendingBindings()
    }

    fun submitBooks(newFruits: MutableList<FruitItem>) {
        //could be better to use DiffUtils for better performance
        fruits = newFruits
        notifyDataSetChanged()
    }
}