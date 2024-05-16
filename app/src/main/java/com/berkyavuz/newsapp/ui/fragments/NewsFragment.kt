package com.berkyavuz.newsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.SearchView
import com.berkyavuz.newsapp.R
import com.berkyavuz.newsapp.databinding.FragmentNewsBinding
import com.berkyavuz.newsapp.ui.adapters.NewsAdapter
import com.berkyavuz.newsapp.ui.viewmodels.NewsViewModel
import com.berkyavuz.newsapp.network.RetrofitClient
import com.berkyavuz.newsapp.model.database.AppDatabase
import com.berkyavuz.newsapp.repository.ArticleRepository

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var articleRepository: ArticleRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleDao = AppDatabase.getDatabase(requireContext()).articleDao()
        val newsService = RetrofitClient.instance
        articleRepository = ArticleRepository(articleDao, newsService)
        viewModel = ViewModelProvider(this, NewsViewModel.Factory(articleRepository)).get(NewsViewModel::class.java)

        setupRecyclerView()
        setupSearchView()
        performSearch("galatasaray")
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            val action = NewsFragmentDirections.actionNewsFragmentToDetailsFragment(article)
            findNavController().navigate(action)
        }
        binding.recyclerViewNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupSearchView() {
        val searchView = requireActivity().findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    performSearch(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun performSearch(query: String) {
        viewModel.getNews(query, "528d24d42f904de19b0fedcd1408bcf6", 1).observe(viewLifecycleOwner, Observer { result ->
            result.fold(onSuccess = { articles ->
                newsAdapter.submitList(articles ?: mutableListOf())
            }, onFailure = {
                // Hata durumunu ele alın
            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
