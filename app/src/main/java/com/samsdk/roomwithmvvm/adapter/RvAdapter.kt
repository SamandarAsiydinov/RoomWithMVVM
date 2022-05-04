package com.samsdk.roomwithmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samsdk.roomwithmvvm.database.UserEntity
import com.samsdk.roomwithmvvm.databinding.ItemLayoutBinding

class RvAdapter : ListAdapter<UserEntity, RvAdapter.RvViewHolder>(DiffCallBack()) {

    lateinit var onDeleteClick: (UserEntity) -> Unit
    lateinit var onItemClick: (UserEntity) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<UserEntity>() {
        override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class RvViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            binding.apply {
                tvEmail.text = user.name
                tvEmail.text = user.email
                tvPhone.text = user.phone
                deleteUserID.setOnClickListener {
                    onDeleteClick.invoke(user)
                }
                itemView.setOnClickListener {
                    onItemClick.invoke(user)
                }
            }
        }
    }
}