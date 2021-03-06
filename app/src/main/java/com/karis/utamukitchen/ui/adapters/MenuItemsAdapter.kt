package com.karis.utamukitchen.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karis.utamukitchen.R
import com.karis.utamukitchen.models.Food
import com.karis.utamukitchen.utils.Onclick
import kotlinx.android.synthetic.main.fooditem.view.*


class FoodAdapter(private var onclick: Onclick) : ListAdapter<Food, FoodAdapter.ViewHolder>(
    DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fooditem, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food = getItem(position)

        setFadeAnimation(holder.itemView)
        holder.itemView.textViewFoodTitle.text = food.name.toString()
        holder.itemView.textViewPrice.text = "Ksh. ${food.price.toString()}"
        holder.itemView.textViewDescription.text = food.description.toString()
        Glide.with(holder.itemView.context).load(food.image).circleCrop().into(
                holder.itemView.imageViewFoodMenu
        )
        holder.itemView.setOnClickListener {
            onclick.orderActivity(food)
        }
    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
    override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
        return oldItem == newItem
    }

}