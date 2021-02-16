package com.example.minitwitter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minitwitter.databinding.ItenListHomeBinding
import com.example.minitwitter.repository.netWork.json.Twit

class HomeItemAdapter(val twitListener: TwitListener) :
    ListAdapter<Twit, HomeItemAdapter.ItemViewHolder>(TwitComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val twit = getItem(position)
        holder.bind(twit,twitListener)
    }

    class ItemViewHolder private constructor(val binding: ItenListHomeBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Twit, twitListener: TwitListener){
            binding.item = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : ItemViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItenListHomeBinding.inflate(layoutInflater,parent,false)
                return ItemViewHolder(binding)
            }
        }
    }

    class TwitComparator : DiffUtil.ItemCallback<Twit>() {
        override fun areItemsTheSame(oldItem: Twit, newItem: Twit): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Twit, newItem: Twit): Boolean {
            return oldItem.id == newItem.id
        }
    }


}

class TwitListener(val onClickListener : (Twit) -> Unit){
    fun onClick(twit: Twit) = onClickListener(twit)
}