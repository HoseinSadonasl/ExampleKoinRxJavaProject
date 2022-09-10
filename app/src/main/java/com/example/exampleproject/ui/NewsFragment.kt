package com.example.exampleproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.exampleproject.R
import com.example.exampleproject.adapter.NewsAdapter
import com.example.exampleproject.databinding.LayoutNewsFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment: Fragment() {

    lateinit var binding: LayoutNewsFragmentBinding
    private val viewModel: NewsViewModel by viewModel()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.layout_news_fragment,
            container, false
        )

        newsAdapter = NewsAdapter()
        viewModel.newsList.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }
        binding.newsRv.adapter = newsAdapter

        return binding.root
    }

}