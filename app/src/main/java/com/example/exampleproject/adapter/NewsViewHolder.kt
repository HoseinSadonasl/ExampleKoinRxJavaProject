package com.example.exampleproject.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.exampleproject.databinding.LayoutNewsItemBinding
import com.example.exampleproject.model.Data

class NewsViewHolder (
    var binding: LayoutNewsItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Data) {
        binding.apply {
            newsTitle.text = data.title
            newsDescription.text = data.description
            publishedtimeTv.text = data.published_at?.substring(0, 10)
            sourceTv.text = data.source
        }
        binding.executePendingBindings()
    }
}