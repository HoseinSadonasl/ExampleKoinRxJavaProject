package com.example.exampleproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.exampleproject.databinding.LayoutNewsItemBinding
import com.example.exampleproject.model.Data

class NewsAdapter: ListAdapter<Data, NewsViewHolder>(newsAdapterDiffUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding: LayoutNewsItemBinding = LayoutNewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

object newsAdapterDiffUtils: DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.title == newItem.title
    }

}