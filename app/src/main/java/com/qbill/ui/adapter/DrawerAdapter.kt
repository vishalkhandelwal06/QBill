package com.qbill.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.qbill.R
import com.qbill.databinding.InflateDrawerItemBinding
import com.qbill.ui.model.DrawerModel
import com.qbill.utils.Conditions.gone
import com.qbill.utils.Conditions.setOnClick
import com.qbill.utils.Conditions.show


class DrawerAdapter(var context: Context, var unit:(DrawerModel, Int)->Unit) :
    ListAdapter<DrawerModel, DrawerAdapter.ViewHolder>(DiffUtils()) {

    class DiffUtils : DiffUtil.ItemCallback<DrawerModel>() {
        override fun areItemsTheSame(oldItem: DrawerModel, newItem: DrawerModel): Boolean =
            oldItem.image == newItem.image

        override fun areContentsTheSame(oldItem: DrawerModel, newItem: DrawerModel): Boolean =
            oldItem.image == newItem.image
    }

    class ViewHolder(var binding: InflateDrawerItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        InflateDrawerItemBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = getItem(position)
        holder.binding.apply {
            if(model.isSelected) {
                ivForward.show()
                root.setBackgroundResource(R.drawable.bg_red_rounded)
            }else{
                ivForward.gone()
                root.setBackgroundResource(android.R.color.transparent)
            }
            Glide.with(context).load(model.image)
                .placeholder(R.drawable.ic_launcher_background).into(ivHome)

            tvName.text = model.text

            root.setOnClick {
                if(currentList.size>0) {
                    currentList.forEachIndexed { index, DrawerModel ->
                        DrawerModel.isSelected = false
                    }
                }
                model.isSelected = !model.isSelected
                notifyDataSetChanged()
                unit(model,position)
            }

        }
    }



}


