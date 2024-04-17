package com.example.irgiuts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.irgiuts.databinding.ListItemBinding

class MainAdapter : ListAdapter<UsersItem, MainAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(usersItem: UsersItem){
            binding.namaProfile.text = usersItem.login
            Glide.with(itemView.context)
                .load(usersItem.avatarUrl)
                .into(binding.fotoProfile)

            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra("nama", usersItem.login)
                it.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UsersItem>(){
            override fun areItemsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UsersItem, newItem: UsersItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}